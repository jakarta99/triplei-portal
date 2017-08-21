package tw.com.triplei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurer")
public class InsurerController {
	@RequestMapping("/list")
	public String list() {
		return "/insurer/list";
	}
}
