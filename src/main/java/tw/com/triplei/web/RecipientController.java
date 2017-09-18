package tw.com.triplei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.triplei.service.ProductService;
import tw.com.triplei.service.RecipientService;

@Controller
@RequestMapping("/recipient")
public class RecipientController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RecipientService recipientService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("models", recipientService.getAll());
		
		return "/recipient/list";
	}
	
	@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
	public String addPage(Model model,@PathVariable("id") final Long id) {
		model.addAttribute("modelp",productService.getOne(id));
		return "/recipient/recipientAdd";
	}
	
	@RequestMapping("/filt")
	public String filt(Model model) {
		
		model.addAttribute("models", recipientService.getAll());
		
		return "/recipient/filt";
	}
	
}
