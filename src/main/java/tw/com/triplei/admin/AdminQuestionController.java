package tw.com.triplei.admin;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import tw.com.triplei.admin.spec.GiftSpecification;
import tw.com.triplei.admin.spec.QuestionSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.QuestionEntity;
import tw.com.triplei.service.GiftService;
import tw.com.triplei.service.QuestionService;

@Slf4j
@Controller
@RequestMapping("/admin/question")
public class AdminQuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/question/questionList";
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

	@RequestMapping(value = "/emailReply", method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<QuestionEntity> emailReply(final Model model, @RequestBody final QuestionEntity form ) {
//		@RequestParam("emailreply") String emailreply
		log.debug("{}", form);
		
//		System.out.println("測試問題回覆 = " + emailreply);
		
		final AjaxResponse<QuestionEntity> response = new AjaxResponse<QuestionEntity>();

		try {
			
			form.setReplyStatus(true);
			final QuestionEntity updateResult = questionService.update(form);
			response.setData(updateResult);

		} catch (final Exception e) {
			response.addException(e);
		}

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom("triplei5432@gmail.com");
			helper.setTo("triplei5432@gmail.com");
			helper.setSubject("主題:回覆Email");
			helper.setText(
					"<html>\r\n" + 
					"<body>\r\n" + 
					"    <h1>你好，${username}這是一封病毒郵件!</h1>\r\n" + 
					"    <h1 style=\\\"color:red;\\\">你中毒了</h1>\r\n" + 
					"    	親愛的${username}  </br></br>\r\n" + 
					"  \r\n" + 
					"		歡迎加入triplei   </br></br>\r\n" + 
					"  \r\n" + 
					"		剛您收到這封信的時候，您的電腦已經中毒了。  </br></br>\r\n" + 
					"		\r\n" + 
					"		請點擊連結繳費解除病毒:${url}  </br></br>\r\n" + 
					"		\r\n" + 
					"		如果您的email信箱不支援連結點擊，請將上面的地址拷貝至您的瀏覽器(如Chrome)的網址欄進入。  </br></br>\r\n" + 
					"		\r\n" + 
					"		如果您還有任何問題，可以聯繫管理員${email}  </br></br>\r\n" + 
					"		  \r\n" + 
					"		我們對您產生的不變，深表歉意。  </br></br>\r\n" + 
					"		  \r\n" + 
					"		希望您在triplei度過快樂的時光!  </br></br>\r\n" + 
					"		      \r\n" + 
					"	    </br></br>\r\n" + 
//					"	    <img src=\\\"cid:virusattack\\\" >\r\n" + 
					"		</hr>\r\n" + 
					"       \r\n" + 
					"		(這是一封自動產生的email，請勿回覆。)  \r\n" + 
					"</body>\r\n" + 
					"</html>",
					true);
//			FileSystemResource file = new FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
//			helper.addInline("virusattack", file);
			mailSender.send(mimeMessage);
		} catch (MailException e) {
			response.addException(e);
		} catch (MessagingException e) {
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

			// final List<AzaleaCriterion> criterions = Lists.newArrayList();
			//
			//
			// if (!Strings.isNullOrEmpty(form.getAccount())) {
			// criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "account",
			// form.getAccount() + "%"));
			// }
			//
			// if (!Strings.isNullOrEmpty(form.getLocalName())) {
			// criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "localName",
			// "%" + form.getLocalName() + "%"));
			// }
			//
			// if (form.getStatus() != null) {
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "status",
			// form.getStatus()));
			// }

			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
			// if (!RoleUtil.isHaveAdminRoles()) {
			// final SecUser loginUser = (SecUser)
			// SecurityUtils.getSubject().getPrincipal();
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId",
			// loginUser.getRootChannelId()));
			// }

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
