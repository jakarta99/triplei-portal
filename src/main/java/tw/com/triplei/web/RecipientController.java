package tw.com.triplei.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.com.triplei.entity.ProductEntity;
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
	
	@RequestMapping(value = "/add/{id}/{bdate}/{gender}/{insureAmount}/{premiumAfterDiscount}/{getPoint}", method = RequestMethod.GET)
	public String addPage(Model model,@PathVariable("id") final Long id,
			@PathVariable("bdate") final String bdate,
			@PathVariable("gender") final String gender,
			@PathVariable("insureAmount") final String insureAmountS,
			@PathVariable("premiumAfterDiscount") final String premiumAfterDiscountS,
			@PathVariable("getPoint") final String getPointS) {
		ProductEntity product = productService.getOneAll(id);
		int year = productService.bDateToInt(bdate);
		product.setInsureAmount(BigDecimal.valueOf(Double.parseDouble(insureAmountS)));
		product.setPremiumAfterDiscount(BigDecimal.valueOf(Double.parseDouble(premiumAfterDiscountS)));
		product.setGetPoint(BigDecimal.valueOf(Double.parseDouble(getPointS)));
		
		model.addAttribute("year",year);
		model.addAttribute("model",product);
		return "/product/buyProduct";
	}
	
	@RequestMapping("/filt")
	public String filt(Model model) {
		
		model.addAttribute("models", recipientService.getAll());
		
		return "/recipient/filt";
	}
	
}
