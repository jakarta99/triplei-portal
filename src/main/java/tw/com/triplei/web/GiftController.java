package tw.com.triplei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gift")
public class GiftController {
	@RequestMapping("/list")
	public String list() {
		return "/gift/list";
	}
}
