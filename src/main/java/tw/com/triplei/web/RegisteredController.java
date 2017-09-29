package tw.com.triplei.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import tw.com.triplei.dao.RoleDao;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.EmailService;
import tw.com.triplei.service.AdminUserService;

@Slf4j
@Controller
@RequestMapping("/registered")
public class RegisteredController {
	
	@Autowired
	private AdminUserService userService;
	
	@Autowired
	private EmailService emailservice;
	
//	@Autowired
//	private RoleDao roleDao;
//	
//	@Autowired
//	private UserDao userDao;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/registered/createAccount";
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
	
	@RequestMapping(value = "/sendNewPassword")
	public String sendNewPasswordPage(final Model model, @RequestParam(value="id") Integer id) {
		log.debug("form :{}",id);
		int userId = id;
		UserEntity entity = userService.getOne(id.longValue());
		log.debug("entity :{}", entity);
		
		model.addAttribute("resendUserEntity", entity);
		
		return "/registered/sendNewPassword";
	}
	
	@RequestMapping(value = "/sendNewPasswordEMail")
	@ResponseBody
	public AjaxResponse<UserEntity> sendNewPasswordEMail(final Model model, @RequestBody final UserEntity form) {
		AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		log.debug("sendNewPasswordEMail");
		
		try{
			
			UserEntity entity = userService.getOne(form.getId());
			log.debug("sendNewPasswordEMail entity {}:", entity);
			emailservice.sendNewPassword(entity);// 發送忘記密碼的臨時新密碼
			
		}catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("{}", response);
		
		return response;
		
	}
	
	@RequestMapping(value="genNewPassword", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> genNewPassword(final Model model, @RequestBody final UserEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
			UserEntity entity = userService.getByAccountNumber(form.getAccountNumber());
			List<Message> messages = new ArrayList<Message>();
			if(entity == null){
				messages.add(Message.builder().code("accountNumber").value("註冊帳號不存在").build());
				response.addMessages(messages);
			}else {
				final UserEntity updateResult = userService.updateForgetPassword(entity);
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
	
	
	
	@RequestMapping(value = "/sendCheckLetter", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> sendCheckLetterPage(final Model model, @RequestBody final UserEntity form) {
		
		AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		log.debug("sendCheckLetterPage");
		try {

			//UserEntity userEntity = userDao.findByEmail(form.getEmail()); // FIXME controller不能直接用Dao
			//UserEntity userEntity = userService.getByEmail(form.getEmail());
			UserEntity userEntity = userService.getOne(form.getId());
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
		UserEntity userEntity = userService.getOne(id.longValue());
		model.addAttribute("resendInfo", userEntity);
		
		return "/registered/checkLetter";
	}
	

	
	
	@RequestMapping(value = "/checked")
	public String checkedPage(Model model, @RequestParam(value="uid", required = true) String uid) {
		// 正向
		log.debug("checkedPage uid = {}",uid);
//		UserEntity userEntity = userDao.findByRegisteredCode(uid);
		UserEntity userEntity = userService.getByRegisteredCpde(uid);
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
					userService.update(userEntity);
					return "/registered/checkedSuccess";
				} else {
					// 驗證碼已失效，同時刪除該會員
					userService.delete(userEntity.getId());
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
			
			
			form.setAudittingPoint(0);
			form.setRemainPoint(0);
			form.setExchangedPoint(0);
			final UserEntity insertResult = userService.insert(form);
			response.setData(insertResult);
			
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("{}", response);
		
		return response;
	}
	


}
