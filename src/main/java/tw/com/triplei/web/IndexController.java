package tw.com.triplei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.enums.ArticleType;
import tw.com.triplei.service.ArticleService;
import tw.com.triplei.service.GetImageService;
import tw.com.triplei.service.ProductService;
import tw.com.triplei.service.UserService;

@Controller
@Slf4j
public class IndexController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private GetImageService getImageService;
	

	@RequestMapping("/")
	public String index(Model model) {
		
		List<ArticleEntity> newsList = articleService.getArticlesByHotArticle(ArticleType.NEWS, true, true);
		for(ArticleEntity article:newsList){
			article.setShowImage(getImageService.getImage(article.getBannerImage()));
		}
		model.addAttribute("news", newsList);
	
		List<ArticleEntity> hotIssueList = articleService.getHotArticles(true, true);
		for(ArticleEntity article:hotIssueList){
			article.setShowImage(getImageService.getImage(article.getBannerImage()));
		}
		model.addAttribute("hotissue", hotIssueList);
		
		List<ProductEntity> hotProduct = productService.getHotProduct();
		model.addAttribute("hotproduct", hotProduct);
		
		//TODO 進入首頁，若為會員，檢查基本資料是否填寫完畢，若否，請他填寫完基本資料
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			log.debug("user login");
			if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
				log.debug("user is not anonymousUser");
				UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
				if (!details.getUsername().contains("@")){
					
					UserEntity entity = userService.getByAccountNumber(details.getUsername());
					
					model.addAttribute("userDetails", entity);
					return "/user/userEdit";
				}
			}

		}
		
		return "/index";
	}
}