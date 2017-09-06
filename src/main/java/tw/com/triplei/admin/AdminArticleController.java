package tw.com.triplei.admin;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.ArticleSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.service.ArticleService;

@Slf4j
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/*管理者登入後，從首頁點入到文章列表管理*/
	@RequestMapping("/getArticles")
	public String getArticles(Model model){
		
//		try{
//			if(id!=null){
//		ArticleEntity article = articleService.getOne(id);
//		model.addAttribute("articleEntity",article);
//			}
//		}catch(NullPointerException e){
//			log.error("/getArticles-->NullPointerException");
//		}
		return "/admin/article/articleList";
	}
	
	/*管理者點入到文章新增頁面*/
	@RequestMapping("/insertArticle")
	public String insertArticle(Model model){
		
		return "/admin/article/articleAdd";
	}
	
	/*管理者點入到文章修改，刪除頁面*/
	@RequestMapping("/updateDeleteArticle")
	public String updateDeleteArticle(){
		
		return "/admin/article/articleEdit";
	}
	
	/*管理者在文章列表選修改文章需重拉資訊的方法，並轉到修改頁面*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {
		
		ArticleEntity dbEntity = articleService.getOne(id);
		model.addAttribute("entity", dbEntity);
		
		return "/admin/article/articleEdit";
	}
	
	/*管理者在文章列表拉資料的方法*/
	@GetMapping
	@ResponseBody
	public GridResponse<ArticleEntity> query(final Model model, final ArticleEntity form, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<ArticleEntity> page;	
		try {

//			final List<AzaleaCriterion> criterions = Lists.newArrayList();
//
//
//			if (!Strings.isNullOrEmpty(form.getAccount())) {
//				criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "account", form.getAccount() + "%"));
//			}
//
//			if (!Strings.isNullOrEmpty(form.getLocalName())) {
//				criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "localName", "%" + form.getLocalName() + "%"));
//			}
//
//			if (form.getStatus() != null) {
//				criterions.add(new AzaleaCriterion(QueryOpType.EQ, "status", form.getStatus()));
//			}

			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
//			if (!RoleUtil.isHaveAdminRoles()) {
//				final SecUser loginUser = (SecUser) SecurityUtils.getSubject().getPrincipal();
//				criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId", loginUser.getRootChannelId()));
//			}

			page = articleService.getAll(new ArticleSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	
	/*管理者在新增文章的方法*/
	@PostMapping
//	@RequestMapping(value="/new",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<ArticleEntity> insert(final Model model, @RequestBody ArticleEntity form) {
		
		AjaxResponse<ArticleEntity> response = new AjaxResponse<ArticleEntity>();
		
		try {
			System.out.println(form.getBannerImage());
			System.out.println(form.getFile());
			System.out.println(form.getContent());

			LocalDateTime publishTime = LocalDateTime.now();
			form.setPublishTime(publishTime);
			final ArticleEntity insertResult = articleService.insert(form);
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
	
	 /*管理者在修改文章的方法*/
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<ArticleEntity> update(final Model model, @RequestBody final ArticleEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<ArticleEntity> response = new AjaxResponse<ArticleEntity>();
		
		try {
			form.setPublishTime(LocalDateTime.now());
			final ArticleEntity updateResult = articleService.update(form);
			response.setData(updateResult);
			
		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}
	
	 /*管理者要刪除文章的方法*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<ArticleEntity> delete(@PathVariable(value = "id") final long id) {
		
		log.debug("{}", id);
		
		final AjaxResponse<ArticleEntity> response = new AjaxResponse<ArticleEntity>();
		
		try {			
			articleService.delete(id);
		
			
		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;			
	}
}
