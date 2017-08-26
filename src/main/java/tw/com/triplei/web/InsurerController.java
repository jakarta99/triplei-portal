package tw.com.triplei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.triplei.service.InsurerService;

@Controller
@RequestMapping("/insurer")
public class InsurerController {
	
	@Autowired
	private InsurerService insurerService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("models", insurerService.getAll());
		
		return "/insurer/list";
	}
	
}
