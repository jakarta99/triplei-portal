package tw.com.triplei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.dao.RoleDao;
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
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/registered/createAccount";
	}
	
	@PostMapping
	@ResponseBody
	public AjaxResponse<UserEntity> insert(final Model model, @RequestBody final UserEntity form) {
		
		AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
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
