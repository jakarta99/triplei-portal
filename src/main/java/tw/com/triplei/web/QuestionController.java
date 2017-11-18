package tw.com.triplei.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.entity.QuestionEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.EmailService;
import tw.com.triplei.service.QuestionService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;  	

	@Autowired
	private EmailService emailService;  	

	@Autowired
	private UserService userservice;  	
	
	@RequestMapping("/test")
	public String test(Model model) {
		
		
		return "/question/test";
	}
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("questions", questionService.getAll());
		
		return "/question/list";
	}
	@RequestMapping("/askQuestion")
	public String askQuestion() {
		
		return "/question/questionAdd";
	}
	
	@PostMapping
	@ResponseBody
	public AjaxResponse<QuestionEntity> insert(final Model model, @RequestBody QuestionEntity form) {
		
		AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();
		
		try {
			log.debug("QuestionForm = {}", form);
			
			LocalDateTime postTime = LocalDateTime.now();
			form.setPostTime(postTime);
			form.setCreatedTime(new Timestamp(new Date().getTime()));
			final QuestionEntity insertResult = questionService.insert(form);
			response.setData(insertResult);
			
			String userName ;
			UserEntity user =  userservice.getDao().findByEmail(form.getAskerEmail());
			log.debug("問題user = {}" + user);
			
			if(user!=null) {
			 userName =user.getName();
			}else{
			 userName = "";
			}
			
			emailService.sendConfirmEmail(form.getAskerEmail(), postTime, userName, form.getContent());
		
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
