package tw.com.triplei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.enums.ArticleType;
import tw.com.triplei.service.ArticleService;

@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/*用戶的從首頁點入到文章專區首頁 */
	@RequestMapping("/list")
	public String list(Model model) {

		List<ArticleEntity> list= articleService.getBannerRotationArticles(true, true);
		model.addAttribute("bannerRotation",list);
		
		List<ArticleEntity> editorChoiceList = articleService.getArticlesByHotArticle(ArticleType.EDITOR_CHOICE, true, true);
		model.addAttribute("editorChoice", editorChoiceList);
		
		List<ArticleEntity> newsList = articleService.getArticlesByHotArticle(ArticleType.NEWS, true, true);
		model.addAttribute("news", newsList);
		
		List<ArticleEntity> goodReadList = articleService.getArticlesByHotArticle(ArticleType.GOODREAD, true, true);
		model.addAttribute("goodRead", goodReadList);
		
		List<ArticleEntity> investmentTipsList = articleService.getArticlesByHotArticle(ArticleType.INVESTMENT_TIPS, true, true);
		model.addAttribute("investmentTips", investmentTipsList);

		log.debug("sortList{}",list);
		return "/article/list";
	}
	
	/*用戶從文章首頁點入到小資族必讀專區 */
	@RequestMapping("/goodRead")
	public String goodRead(Model model){
		List<ArticleEntity> articles= articleService.getArticlesByTypes(ArticleType.GOODREAD,true);
		model.addAttribute("articles",articles);

		return "/article/goodReadSection";
	}
	
	/*用戶從文章首頁點入到新聞專區 */
	@RequestMapping("/news")
	public String news(Model model){
		List<ArticleEntity> articles= articleService.getArticlesByTypes(ArticleType.NEWS,true);
		model.addAttribute("articles",articles);
		
		return "/article/newsSection";
	}
	
	/*用戶從文章首頁點入到理財觀念專區 */
	@RequestMapping("/investmentTips")
	public String investmentTips(Model model){
		List<ArticleEntity> articles= articleService.getArticlesByTypes(ArticleType.INVESTMENT_TIPS,true);
		model.addAttribute("articles",articles);
		
		return "/article/investmentTipsSection";
	}
	
	/*用戶從文章首頁點入到編輯精選專區 */
	@RequestMapping("/editorChoice")
	public String editorChoice(Model model){
		List<ArticleEntity> articles= articleService.getArticlesByTypes(ArticleType.EDITOR_CHOICE,true);
		model.addAttribute("articles",articles);
		
		return "/article/editorChoiceSection";
	}
	
	/*用戶從某文章類別專區點入到文章 */
	@RequestMapping(value = "/readArticle/read/{id}",method=RequestMethod.GET)
	public String readArticle(@PathVariable("id") final Long id, Model model){
		log.debug("id{}",id);
		ArticleEntity article = articleService.getOne(id);
		model.addAttribute("article",article);
		log.debug("DB article clickCount{}",article.getClickCount());
		article.setClickCount((article.getClickCount()+1));
		log.debug("+clickCount{}",article.getClickCount());
		articleService.update(article);
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