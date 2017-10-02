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
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.enums.ArticleType;
import tw.com.triplei.service.ArticleService;
import tw.com.triplei.service.UserService;

@Controller
@Slf4j
public class IndexController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(Model model) {
		
		List<ArticleEntity> newsList = articleService.getArticlesByHotArticle(ArticleType.NEWS, true, true);
		model.addAttribute("news", newsList);
	
		List<ArticleEntity> hotIssueList = articleService.getHotArticles(true, true);
		model.addAttribute("hotissue", hotIssueList);
		
		
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