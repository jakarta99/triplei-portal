package tw.com.triplei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping("/own")
	public String list(Model model) {
		
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("details: {}", details.getUsername());
		UserEntity entity = userDao.findByAccountNumber(details.getUsername());
		log.debug("principal user accountNumber: {}" , entity);
		
//		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//		log.debug(authorities.toString());
		
		model.addAttribute("userDetails", entity);
		
		return "/user/userEdit";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> update(final Model model, @RequestBody final UserEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
			
			final UserEntity updateResult = userService.update(form);
			log.debug("updateResult {}",updateResult);
			response.setData(updateResult);
			
			
		} catch (final Exception e) {
			response.addException(e);
		}		
		return response;
	}

}
