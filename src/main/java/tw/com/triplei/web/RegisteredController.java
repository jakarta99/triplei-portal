package tw.com.triplei.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
import tw.com.triplei.dao.RoleDao;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.EmailService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/registered")
public class RegisteredController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/registered/createAccount";
	}
	
	@RequestMapping(value = "/checked")
	public String checkedPage(Model model, @RequestParam(value="uid", required = false) String uid) {
		// 正向
		log.debug("checkedPage uid = {}",uid);
		UserEntity userEntity = userDao.findByRegisteredCode(uid);
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
			// TODO 帳號不得重複註冊
			final UserEntity insertResult = userService.insert(form);
			response.setData(insertResult);
			
			emailservice.sendRegisteredEmail(insertResult); // 發送註冊信
			
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
