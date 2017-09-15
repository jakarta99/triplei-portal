package tw.com.triplei.web;

import java.time.LocalDateTime;

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
import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.entity.QuestionEntity;
import tw.com.triplei.service.QuestionService;

@Slf4j
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;  	
	
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
	
	/*管理者在新增文章的方法*/
	@PostMapping
	@ResponseBody
	public AjaxResponse<QuestionEntity> insert(final Model model, @RequestBody QuestionEntity form) {
		
		AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();
		
		try {
			
			LocalDateTime publishTime = LocalDateTime.now();
			form.setPostTime(publishTime);
			final QuestionEntity insertResult = questionService.insert(form);
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
