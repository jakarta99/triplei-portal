package tw.com.triplei.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.ArticleSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.service.ArticleService;

@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ArticleService articleService;
	
	/*用戶的從首頁點入到文章專區首頁 */
	@RequestMapping("/list")
	public String list(Model model) {
		
		Map<String,String>content = new HashMap<>();
		content.put("editorChoice", env.getProperty("editor.choice"));
		content.put("news", env.getProperty("news"));
		content.put("goodRead", env.getProperty("good.read"));
		content.put("investmentTips", env.getProperty("investment.tips"));
		
		content.put("carousel1url", env.getProperty("carousel1.url"));
		content.put("carousel1ImagePath", env.getProperty("carousel1.imagePath"));
		content.put("carousel1Title",env.getProperty("carouse1.title"));
		content.put("carousel2url", env.getProperty("carousel2.url"));
		content.put("carousel2ImagePath", env.getProperty("carousel2.imagePath"));
		content.put("carousel2Title",env.getProperty("carouse2.title"));
		content.put("carousel3url", env.getProperty("carousel3.url"));
		content.put("carousel3ImagePath", env.getProperty("carousel3.imagePath"));
		content.put("carousel3Title",env.getProperty("carouse3.title"));
		
		model.addAllAttributes(content);
		
		return "/article/list";
	}
	
	/*用戶從文章首頁點入到小資族必讀專區 */
	@RequestMapping("/goodRead")
	public String goodRead(Model model){
		
		return "/article/goodReadSection";
	}
	
	/*用戶從文章首頁點入到新聞專區 */
	@RequestMapping("/news")
	public String news(Model model){
		
		return "/article/newsSection";
	}
	
	/*用戶從文章首頁點入到理財觀念專區 */
	@RequestMapping("/investmentTips")
	public String investmentTips(Model model){
		
		return "/article/investmentTipsSection";
	}
	
	/*用戶從文章首頁點入到編輯精選專區 */
	@RequestMapping("/editorChoice")
	public String editorChoice(Model model){
		
		List<ArticleEntity> articles= articleService.getAll();
		
		
		model.addAttribute("articles",articles);
		
		return "/article/editorChoiceSection";
	}
	
	/*用戶從某文章類別專區點入到文章 */
	@RequestMapping(value = "/readArticle/read/{id}",method=RequestMethod.GET)
	public String readArticle(@PathVariable("id") final Long id, Model model){
		ArticleEntity article = articleService.getOne(id);
		model.addAttribute("article",article);
		return "/article/readArticle";
	}
	
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
		
		return "/admin/article/articleInsert";
	}
	
	/*管理者點入到文章修改，刪除頁面*/
	@RequestMapping("/updateDeleteArticle")
	public String updateDeleteArticle(){
		
		return "/admin/article/articleUpdate";
	}
	
	/*管理者在文章列表選修改文章需重拉資訊的方法，並轉到修改頁面*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {
		
		ArticleEntity dbEntity = articleService.getOne(id);
		model.addAttribute("entity", dbEntity);
		
		return "/admin/article/articleUpdate";
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
	@ResponseBody
	public AjaxResponse<ArticleEntity> insert(final Model model, @RequestBody ArticleEntity form) {
		
		AjaxResponse<ArticleEntity> response = new AjaxResponse<ArticleEntity>();
		
		try {
			System.out.println(form.getBannerImage());
//			BufferedOutputStream stream = null;
//			if(!file.isEmpty()){
//				try{
//				byte[] bytes= file.getBytes();
//				
//				String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "tmpFiles");
//				
//				if(!dir.exists())
//					dir.mkdirs();
//					
//					File serverFile = new File(dir.getAbsolutePath()
//							+ File.separator + form.getTitle());
//					stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//					stream.write(bytes);
//
//					log.info("Server File Location="
//							+ serverFile.getAbsolutePath());
//
//					System.out.println("You successfully uploaded file=" + form.getTitle());
//				} catch (Exception e) {
//					System.out.println("You failed to upload " +form.getTitle() + " => " + e.getMessage());
//				}finally{
//					stream.close();}
//			} else {
//				System.out.println("You failed to upload " + form.getTitle()+ " because the file was empty.");
//			}
			
			LocalDateTime publishTime = LocalDateTime.now();
			System.out.println(publishTime);
			form.setPublishTime(publishTime);
			final ArticleEntity insertResult = articleService.insert(form);
			response.setData(insertResult);
			System.out.println(insertResult.getPublishTime());
		
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
	
	/*頁面文章點擊積分*/
	@RequestMapping("/count")
	public void count(final ArticleEntity article){
		try{
		if(article.getId()!=null){
			ArticleEntity plus = articleService.getOne(article.getId());
			int count = plus.getClickCount();
			count++;
			plus.setClickCount(count);
			articleService.update(plus);
		}else{
			log.error("/Count --> Article not found");
		}
		}catch(NullPointerException e){
			log.error("/Count --> NullPointerException");
		}
	}
	
}
