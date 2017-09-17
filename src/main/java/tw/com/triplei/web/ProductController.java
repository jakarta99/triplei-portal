package tw.com.triplei.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.RecipientEntity;
import tw.com.triplei.enums.Currency;
import tw.com.triplei.service.ProductService;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Currency curr;
	
	@RequestMapping("/list")
	public String list(Model model) {
		return "/product/list";
	}

	@GetMapping("/{id}")
	public String detailInfo(@PathVariable("id") String id, Model model) {
		return "/product/detailInfo";
	}
	
	@GetMapping("buyProduct/{currency}/{insureAmount}/{premium}/{points}")
	public String buyProduct(@PathVariable("currency")String currency,
			@PathVariable("insureAmount")String insureAmount,
			@PathVariable("premium")String premium,@PathVariable("points")String points,Model model){
		
		return "/product/buyProduct";
	}

	@RequestMapping(value = "getProduct/{gender}/{bDate}/{currency}/{paymentMethod}/{interestRateType}/{premium}/{year}/{yearCode}", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResponse<ProductEntity> getProduct(@PathVariable("gender") String gender,
			@PathVariable("bDate") String bDate, @PathVariable("currency") String currency,
			@PathVariable("paymentMethod") String paymentMethod,
			@PathVariable("interestRateType") String interestRateType, @PathVariable("premium") String premium,
			@PathVariable("year") String year, @PathVariable("yearCode") String yearCode,
			ProductEntity pe, RecipientEntity re,BigDecimal bigDecimal) {
		AjaxResponse<ProductEntity> response = new AjaxResponse<ProductEntity>();
		try {
//			curr= productService.stringToCurrency(currency);
			pe.setCurr(curr);
			pe.setPaymentMethod(paymentMethod);
			int yearINT = productService.stringToInt(year);
			pe.setYear(yearINT);
			pe.setYearCode(yearCode);
			bigDecimal = productService.stringToBigDecimal(premium);
			pe.setPremium(bigDecimal);
			pe.setInterestRateType(interestRateType);
			re.setGender(gender);
			//setbirthday
			

		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}

		log.debug("{}", response);

		return response;
	}

}
