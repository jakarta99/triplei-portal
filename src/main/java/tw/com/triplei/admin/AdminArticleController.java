package tw.com.triplei.admin;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import tw.com.triplei.enums.ArticleType;
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
			page = articleService.getAll(new ArticleSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	
	/*管理者在新增文章的方法*/
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<ArticleEntity> insert(@RequestParam(value = "upload-file") MultipartFile file, 
			@RequestParam(value="articleType",required=false)ArticleType articleType, @RequestParam(value="title",required=false) String title,
			@RequestParam(value="introduction",required=false)String introduction,@RequestParam(value="content",required=false)String content,
			@RequestParam(value="author",required=false)String author,@RequestParam(value="bannerRotation",required=false)Boolean bannerRotation,
			@RequestParam(value="hotArticle",required=false)Boolean hotArticle,@RequestParam(value="storeShelves",required=false)Boolean storeShevles,
			ArticleEntity articleEntity){
		AjaxResponse<ArticleEntity> response = new AjaxResponse<ArticleEntity>();
		
		try {
			if(!file.isEmpty()){
				String bannerImage= articleService.imageUpload(file);
				articleEntity.setBannerImage(bannerImage);
				}else{
					articleEntity.setBannerImage("");
				}
			articleEntity.setArticleType(articleType);
			articleEntity.setAuthor(author);
			articleEntity.setBannerRotation(bannerRotation);
			articleEntity.setContent(content);
			articleEntity.setHotArticle(hotArticle);
			articleEntity.setIntroduction(introduction);
			articleEntity.setStoreShelves(storeShevles);
			articleEntity.setTitle(title);
		
			LocalDateTime publishTime = LocalDateTime.now();
			articleEntity.setPublishTime(publishTime);
			final ArticleEntity insertResult = articleService.insert(articleEntity);
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
	public AjaxResponse<ArticleEntity> update(
			@RequestParam(value= "upload-file") MultipartFile file, @RequestParam("id")String id,
			@RequestParam("articleType")ArticleType articleType, @RequestParam("title") String title,
			@RequestParam("introduction")String introduction,@RequestParam("content")String content,
			@RequestParam("author")String author,@RequestParam("bannerRotation")Boolean bannerRotation,
			@RequestParam("hotArticle")Boolean hotArticle,@RequestParam("storeShelves")Boolean storeShevles,
			ArticleEntity articleEntity) {

		final AjaxResponse<ArticleEntity> response = new AjaxResponse<ArticleEntity>();
		
		try {
			if(!file.isEmpty()){
				String bannerImage= articleService.imageUpload(file);
				articleEntity.setBannerImage(bannerImage);
			}else{
				articleEntity.setBannerImage("");
			}
			Long newId= Long.parseLong(id);
			articleEntity.setId(newId);
			articleEntity.setArticleType(articleType);
			articleEntity.setAuthor(author);
			articleEntity.setBannerRotation(bannerRotation);
			articleEntity.setContent(content);
			articleEntity.setHotArticle(hotArticle);
			articleEntity.setIntroduction(introduction);
			articleEntity.setStoreShelves(storeShevles);
			articleEntity.setTitle(title);
		
			LocalDateTime publishTime = LocalDateTime.now();
			articleEntity.setPublishTime(publishTime);
			final ArticleEntity insertResult = articleService.update(articleEntity);
			response.setData(insertResult);
			
		} catch (final Exception e) {
			response.addException(e);
		}
		log.debug("{}", response);
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
