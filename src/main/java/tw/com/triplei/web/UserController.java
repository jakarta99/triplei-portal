package tw.com.triplei.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/user/reset")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping("/info")
	public String infoPage(Model model) {
		
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("details: {}", details.getUsername());
		UserEntity entity;
		if(!details.getUsername().contains("@")){
			entity = userDao.findByProviderUserId(details.getUsername());
		} else {
			entity = userDao.findByAccountNumber(details.getUsername());
		}
		
		log.debug("principal user accountNumber: {}" , entity);
		
//		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//		log.debug(authorities.toString());
		
		model.addAttribute("userDetails", entity);
		
		return "/user/userEdit";
	}
	
	//判斷email是否重複
		@RequestMapping(value = "/check/{userAccountNumber}/{email:.+}", method = RequestMethod.GET)
		@ResponseBody
		public boolean findEmail(Model model,@PathVariable String email,@PathVariable String userAccountNumber) {
			log.debug("email={}",email);
			log.debug("userAccountNumber={}",userAccountNumber);
			
			List<UserEntity> users = userService.getDao().findAll();
			for(UserEntity user:users){
				if(email.equals(user.getEmail()) && !user.getEmail().equals(userAccountNumber)){
					return false;
				}
			}
			return true;
		}
	
	@RequestMapping("/pw")
	public String pwPage(Model model) {
		
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("details: {}", details.getUsername());
		
		UserEntity entity = userDao.findByAccountNumber(details.getUsername());
		log.debug("principal user accountNumber: {}" , entity);
		
		//TODO 如果是FB登入的不能更改帳號密碼
		
		
		model.addAttribute("userDetails", entity);
		
		return "/user/userPwEdit";
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
