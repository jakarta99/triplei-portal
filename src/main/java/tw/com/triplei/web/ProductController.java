package tw.com.triplei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping("/list")
	public String list() {
		return "/product/list";
	}
	
	
}
