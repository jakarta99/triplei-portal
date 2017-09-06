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
