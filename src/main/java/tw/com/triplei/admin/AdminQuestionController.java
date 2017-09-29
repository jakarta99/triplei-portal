package tw.com.triplei.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.QuestionSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.QuestionEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.EmailService;
import tw.com.triplei.service.QuestionService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/admin/question")
public class AdminQuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private EmailService emailservice;

	@Autowired
	private UserService userservice;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/question/questionList";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String returnIndex(Model model) {
		return "/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/admin/question/questionAdd";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		QuestionEntity dbEntity = questionService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/admin/question/questionEdit";
	}

	@RequestMapping(value = "/{id}/emailResponse", method = RequestMethod.GET)
	public String emailResponse(@PathVariable("id") final Long id, Model model) {

		QuestionEntity dbEntity = questionService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/admin/question/questionEmailResponse";
	}

	@RequestMapping(value = "/emailReply/{emailreply}", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<QuestionEntity> emailReply(final Model model, @RequestBody final QuestionEntity form , 
			@PathVariable("emailreply") String emailreply ) {
//		@RequestParam("emailreply") String emailreply

		log.debug("{}", form);
		log.debug("問題Error檢查 = {}" + model.toString());
		log.debug("問題回覆個資 = {}" + form);
		log.debug("問題回覆文字 = {}" + emailreply);
		
		String userName ;
		UserEntity user =  userservice.getDao().findByEmail(form.getAskerEmail());
		log.debug("問題user = {}" + user);
		
		if(user!=null) {
		 userName =user.getName();
		}else{
		 userName = "";
		}
		
		emailservice.sendReplyEmail(form.getAskerEmail(), emailreply, 
				form.getPostTime(), userName, form.getContent());
		
		final AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();

		try {
			
//			form.setReplyStatus(true);
			final QuestionEntity updateResult = questionService.update(form);
			response.setData(updateResult);

		} catch (final Exception e) {
			response.addException(e);
		}

		

		return response;
	}

	@GetMapping
	@ResponseBody
	public GridResponse<QuestionEntity> query(final Model model, final QuestionEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<QuestionEntity> page;

		try {

			page = questionService.getAll(new QuestionSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

	@PostMapping
	@ResponseBody
	public AjaxResponse<QuestionEntity> insert(final Model model, @RequestBody final QuestionEntity form) {

		AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();

		try {

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

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<QuestionEntity> update(final Model model, @RequestBody final QuestionEntity form) {

		log.debug("{}", form);

		final AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();

		try {
			System.out.println(form);
			final QuestionEntity updateResult = questionService.update(form);
			response.setData(updateResult);

		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<QuestionEntity> delete(@PathVariable(value = "id") final long id) {

		log.debug("{}", id);

		final AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();

		try {
			questionService.delete(id);

		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;

	}

}
