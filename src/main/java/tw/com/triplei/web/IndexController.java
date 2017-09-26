package tw.com.triplei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.enums.ArticleType;
import tw.com.triplei.service.ArticleService;
import tw.com.triplei.service.ProductService;

@Controller

public class IndexController {
	
	@Autowired
	private ArticleService articleService;
	private ProductService productService;

	@RequestMapping("/")
	public String index(Model model) {
		
		List<ArticleEntity> newsList = articleService.getArticlesByHotArticle(ArticleType.NEWS, true, true);
		model.addAttribute("news", newsList);
	
		List<ArticleEntity> hotIssueList = articleService.getHotArticles(true, true);
		model.addAttribute("hotissue", hotIssueList);
		
		return "/index";
	}
}