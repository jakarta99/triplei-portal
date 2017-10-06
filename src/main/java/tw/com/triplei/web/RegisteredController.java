package tw.com.triplei.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.Message;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.AdminUserService;
import tw.com.triplei.service.EmailService;
import tw.com.triplei.service.RoleService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/registered")
public class RegisteredController {
	
	@Autowired
	private AdminUserService adminUserService;  // 註冊用
	
	@Autowired
	private UserService userService; // 忘記密碼用
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/registered/createAccount";
	}
	
	//判斷email是否重複
	@RequestMapping(value = "/check/{email:.+}", method = RequestMethod.GET)
	@ResponseBody
	public boolean findEmail(Model model,@PathVariable String email) {
		log.debug("email={}",email);
		List<UserEntity> users = userService.getDao().findAll();
		for(UserEntity user:users){
			if(email.equals(user.getEmail())){
				return false;
			}
		}
		return true;
	}
	
	
	
	@RequestMapping(value = "/checkedSuccess", method = RequestMethod.GET)
	public String successPage() {
		return "/registered/checkedSuccess";
	}
	
	@RequestMapping(value = "/checkedFailure", method = RequestMethod.GET)
	public String failurePage() {
		return "/registered/checkedFailure";
	}
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String forgetPasswordPage() {
		return "/registered/forgetPassword";
	}
	
	@RequestMapping(value = "/sendPwEditLink")
	public String sendNewPasswordPage(final Model model, @RequestParam(value="id") Integer id) {
		log.debug("form :{}",id);
		int userId = id;
		UserEntity entity = adminUserService.getOne(id.longValue());
		log.debug("entity :{}", entity);
		
		model.addAttribute("resendUserEntity", entity);
		
		return "/registered/sendPwEditLink";
	}
	
	@RequestMapping(value = "/sendPwEditEMail")
	@ResponseBody
	public AjaxResponse<UserEntity> sendPwEditEMail(final Model model, @RequestBody final UserEntity form) {
		AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		log.debug("sendPwEditEMail");
		
		try{
			
			UserEntity entity = adminUserService.getOne(form.getId());
			log.debug("sendPwEditEMail entity {}:", entity);
			emailservice.sendUpdatePasswordLink(entity); // 發送忘記密碼的更信密碼認證連結
			
		}catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("{}", response);
		
		return response;
		
	}
	
	@RequestMapping(value="getForgetPerson", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> getForgetPerson(final Model model, @RequestBody final UserEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
			UserEntity entity = adminUserService.getByAccountNumber(form.getAccountNumber());
			List<Message> messages = new ArrayList<Message>();
			if(entity == null){
				messages.add(Message.builder().code("accountNumber").value("註冊帳號不存在").build());
				response.addMessages(messages);
			}else {
				final UserEntity updateResult = adminUserService.updateForgetPassword(entity);
				log.debug("updateResult {}",updateResult);
				model.addAttribute("resendUserEntity", updateResult);
				response.setData(updateResult);
			}
			
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
			
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("response: {}",response);
		return response;
	}
	
	@RequestMapping(value = "/checkUid")
	public String checkUid(Model model, @RequestParam(value="uid", required = true) String uid) {
		
		// 正向
		log.debug("checkUid uid = {}",uid);
		UserEntity userEntity = adminUserService.getByRegisteredCode(uid);
		log.debug("userEntity user={}",userEntity);
		
		if(userEntity != null){
			// 找到該驗證碼會員，驗證成功 進 updatePassword頁面
			
			Timestamp modifiedDateTime = userEntity.getModifiedTime();
			LocalDateTime modifiedTime = modifiedDateTime.toLocalDateTime();  // Timestamp轉LocalDateTime
			LocalDateTime validTime = modifiedDateTime.toLocalDateTime().plusMinutes(30); // 加0.5hr -> 為該驗證連結的有效時間
			LocalDateTime now = LocalDateTime.now();
			
			log.debug("validTime :{} , now : {}" , validTime, now);
			log.debug("validTime.compareTo(now) : {}" , validTime.compareTo(now));
			log.debug("now.compareTo(validTime) : {}" , now.compareTo(validTime));
			
//			if(now.compareTo(validTime) >=0 ){ // 測試已失效之驗證連結
			if(validTime.compareTo(now) >=0 ){
				
				model.addAttribute("entity", userEntity);
				return "/registered/userForgetPwEdit";  
			} else {
				return "/registered/checkedForgetPwFailure";  //已失效之驗證連結
			}
			

		} else {
			// 找不到該驗證碼會員，驗證失敗 進 失敗頁面
			return "/registered/checkedForgetPwFailure"; 
		}		
	}
	
	
	
	@RequestMapping(value = "/sendCheckLetter", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> sendCheckLetter(final Model model, @RequestBody final UserEntity form) {
		
		AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		log.debug("sendCheckLetterPage");
		try {

			//UserEntity userEntity = userDao.findByEmail(form.getEmail()); // FIXME controller不能直接用Dao
			//UserEntity userEntity = userService.getByEmail(form.getEmail());
			UserEntity userEntity = adminUserService.getOne(form.getId());
			model.addAttribute("resendInfo", userEntity);
			response.setData(userEntity);
			
			emailservice.sendRegisteredEmail(userEntity); // 發送註冊信
			
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("{}", response);

		//return "/registered/checkLetter";
		return response;
	}
	
	@RequestMapping(value = "/checkLetter", method = RequestMethod.GET)
	public String checkLetterPage(final Model model, @RequestParam(value="id", required = true) Integer id) {
		//UserEntity userEntity = userDao.findByEmail(email);
		//UserEntity userEntity = userService.getByEmail(email);
		UserEntity userEntity = adminUserService.getOne(id.longValue());
		model.addAttribute("resendInfo", userEntity);
		
		return "/registered/checkLetter";
	}
	

	
	
	@RequestMapping(value = "/checked")
	public String checkedPage(Model model, @RequestParam(value="uid", required = true) String uid) {
		// 正向
		log.debug("checkedPage uid = {}",uid);
//		UserEntity userEntity = userDao.findByRegisteredCode(uid);
		UserEntity userEntity = adminUserService.getByRegisteredCode(uid);
		log.debug("userEntity user={}",userEntity);
		
		if(userEntity != null){
			// 找到該驗證碼會員
			if(userEntity.getEnabled().equals(false)){
				Timestamp createDateTime = userEntity.getCreatedTime(); 
				LocalDateTime createTime = createDateTime.toLocalDateTime();  // Timestamp轉LocalDateTime
				LocalDateTime validTime = createDateTime.toLocalDateTime().plusDays(3); // LocalDateTime加3天 -> 為該驗證連結的有效時間
				LocalDateTime now = LocalDateTime.now();
				
				log.debug("validTime :{} , now : {}" , validTime, now);
				log.debug("validTime.compareTo(now) : {}" , validTime.compareTo(now));
				log.debug("now.compareTo(validTime) : {}" , now.compareTo(validTime));
				
//				if(now.compareTo(validTime) >=0 ){ // 測試已失效之驗證連結
				if(validTime.compareTo(now) >=0 ){
					userEntity.setEnabled(true);
					adminUserService.update(userEntity);
					return "/registered/checkedSuccess";
				} else {
					// 驗證碼已失效，同時刪除該會員
					adminUserService.delete(userEntity.getId());
					return "/registered/checkedFailure";
				}
			} else {
				// 該會員已啟用，不須再點入連結
				return "/registered/checkedFailure";
			}
		} else {
			// 找不到該驗證碼會員
			return "/registered/checkedFailure";
		}		
	}
	
	@PostMapping
	@ResponseBody
	public AjaxResponse<UserEntity> insert(final Model model, @RequestBody final UserEntity form) {
		
		AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
			
			form.setEnabled(false);  // 預設新註冊的會員不啟用
			form.setAccountNumber(form.getEmail());  // 帳號預設為電子信箱
			form.setEditState("");
			
			form.setAudittingPoint(0);
			form.setRemainPoint(0);
			form.setExchangedPoint(0);
			form.setRemainWishTimes(true);
			
			UserEntity entity ;
			List<Message> messages = validateRegistered(form);
			if (messages != null && messages.size() > 0) {
				throw new ApplicationException(messages);
			} 
			
			if(adminUserService.getByAccountNumber(form.getEmail())!=null){
				
				form.setId(adminUserService.getByAccountNumber(form.getEmail()).getId());
				
				final Set<RoleEntity> RoleEntity = new HashSet<>();
				final RoleEntity roleDefault = roleService.getByCode("ROLE_NORMAL"); // 預設權限為一般會員
				RoleEntity.add(roleDefault);
				
				form.setRoles(RoleEntity);
				
				form.setPassword(encodePasswrod(form.getPassword()));
				
				final UserEntity updateResult = adminUserService.update(form);
				response.setData(updateResult);
			} else {
				final UserEntity insertResult = adminUserService.insert(form);
				response.setData(insertResult);
			}
			
			
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("{}", response);
		
		return response;
	}
	
	public String encodePasswrod(final String rawPassword) {
		if(!StringUtils.isBlank(rawPassword)){
			return passwordEncoder.encode(rawPassword);
		} else {
			return null;
		}
	}
	
	
	private List<Message> validateRegistered(UserEntity entity) {
		List<Message> messages = new ArrayList<Message>();
		
		if(StringUtils.isBlank(entity.getProviderUserId())){
			// 一般會員註冊
			UserEntity dbEntity = adminUserService.getByEmail(entity.getEmail()); // email 不得重複註冊
		
			if (dbEntity != null && entity.getEnabled().equals(true)) {
				messages.add(Message.builder().code("email").value("該電子信箱已註冊").build());
			}
			
			if (StringUtils.isBlank(entity.getPassword())) {
				messages.add(Message.builder().code("password").value("請輸入密碼").build());
			}

			if (StringUtils.isBlank(entity.getCheckPassword())) {
				messages.add(Message.builder().code("checkPassword").value("請輸入確認密碼").build());
			}

			if (!StringUtils.isBlank(entity.getPassword()) && !StringUtils.isBlank(entity.getCheckPassword())) {
				if (!entity.getPassword().equals(entity.getCheckPassword())) {
					messages.add(Message.builder().code("password").value("輸入的密碼不相同，請重新輸入").build());
					messages.add(Message.builder().code("checkPassword").value("輸入的密碼不相同，請重新輸入").build());
				}
			}
			

			log.debug("{}", messages);
		}
		
		return messages;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> update(final Model model, @RequestBody final UserEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
			UserEntity entity = userService.getOne(form.getId());
			log.debug("{}", entity);
			
			final UserEntity updateResult = userService.update(form);
			log.debug("updateResult {}",updateResult);
			response.setData(updateResult);
			
			
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
			
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("response: {}",response);
		return response;
	}
	

}
