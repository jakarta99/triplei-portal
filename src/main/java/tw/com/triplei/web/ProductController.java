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
import tw.com.triplei.service.IRRCaculator;
import tw.com.triplei.service.ProductService;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private IRRCaculator irrCaculator;
	
	@RequestMapping("/list")
	public String list(Model model) {
		return "/product/list";
	}

	@GetMapping("/{id}")
	public String detailInfo(@PathVariable("id") String id, Model model) {
		return "/product/detailInfo";
	}
	
	@RequestMapping("/irr")
	public String irr(Model model) {
		return "/product/irr";
	}

	@GetMapping("/calculationofirr/{premium}/{times}/{period}/{expired}")
	@ResponseBody
	public String getirr(@PathVariable("period")double period,@PathVariable("times") double times,
			@PathVariable("premium")double premium,@PathVariable("expired")double expired, Model model) {
		
		AjaxResponse<String> response = new AjaxResponse<String>();
		double i = irrCaculator.getIRR(period, times, premium, expired);

		String irr = String.valueOf(i*100).substring(0, String.valueOf(i*100).indexOf(".") + 3); //先乘100後取小數點後兩位
		
		return irr;
	}
	
	@GetMapping("/calculationofremuneration/{premium}/{times}/{period}/{expired}")
	@ResponseBody
	public String getremuneration(@PathVariable("period")double period,@PathVariable("times") double times,
			@PathVariable("premium")double premium,@PathVariable("expired")double expired, Model model) {
		
		AjaxResponse<String> response = new AjaxResponse<String>();
		double i = irrCaculator.getRemuneration(period, times, premium, expired);
		
		String remuneration = String.valueOf(i);
		
		return remuneration;
	}
	
	@GetMapping("/calculationoftotal/{premium}/{times}/{period}")
	@ResponseBody
	public String gettotal(@PathVariable("period")double period,@PathVariable("times") double times,
			@PathVariable("premium")double premium,Model model) {
		
		AjaxResponse<String> response = new AjaxResponse<String>();
		double i = irrCaculator.getTotal(period, times, premium);
		
		String total = String.valueOf(i);
		
		return total;
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
			if(currency.equals("新台幣")){
				pe.setCurr(Currency.TWD);
			}else if(currency.equals("美金")){
				pe.setCurr(Currency.USD);
			}else if(currency.equals("人民幣")){
				pe.setCurr(Currency.RMB);
			}else if(currency.equals("澳幣")){
				pe.setCurr(Currency.AUD);
			}
			pe.setPaymentMethod(paymentMethod);
			int yearINT = productService.stringToInt(year);
			pe.setYear(yearINT);
			pe.setYearCode(yearCode);
			bigDecimal = productService.stringToBigDecimal(premium);
			pe.setPremium(bigDecimal);
			pe.setInterestRateType(interestRateType);
			re.setGender(gender);
			//setbirthday
			int age = productService.bDateToInt(bDate);
			System.out.println("!!!!!!!!!age="+age);
			

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
