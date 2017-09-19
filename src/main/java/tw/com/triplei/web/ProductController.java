package tw.com.triplei.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import tw.com.triplei.entity.ProductHighDiscountRatio;
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
	private IRRCaculator iRRCaculator;

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
	public String getirr(@PathVariable("period") double period, @PathVariable("times") double times,
			@PathVariable("premium") double premium, @PathVariable("expired") double expired, Model model) {

		AjaxResponse<String> response = new AjaxResponse<String>();
		double i = irrCaculator.getIRR(period, times, premium, expired);

		String irr = String.valueOf(i * 100).substring(0, String.valueOf(i * 100).indexOf(".") + 3); // 先乘100後取小數點後兩位

		return irr;
	}

	@GetMapping("/calculationofremuneration/{premium}/{times}/{period}/{expired}")
	@ResponseBody
	public String getremuneration(@PathVariable("period") double period, @PathVariable("times") double times,
			@PathVariable("premium") double premium, @PathVariable("expired") double expired, Model model) {

		AjaxResponse<String> response = new AjaxResponse<String>();
		double i = irrCaculator.getRemuneration(period, times, premium, expired);

		String remuneration = String.valueOf(i);

		return remuneration;
	}

	@GetMapping("/calculationoftotal/{premium}/{times}/{period}")
	@ResponseBody
	public String gettotal(@PathVariable("period") double period, @PathVariable("times") double times,
			@PathVariable("premium") double premium, Model model) {

		AjaxResponse<String> response = new AjaxResponse<String>();
		double i = irrCaculator.getTotal(period, times, premium);

		String total = String.valueOf(i);

		return total;
	}

	@GetMapping("buyProduct/{currency}/{insureAmount}/{premium}/{points}")
	public String buyProduct(@PathVariable("currency") String currency,
			@PathVariable("insureAmount") String insureAmount, @PathVariable("premium") String premium,
			@PathVariable("points") String points, Model model) {

		return "/product/buyProduct";
	}

	@RequestMapping(value = "getProduct/{gender}/{bDate}/{currency}/{paymentMethod}/{interestRateType}/{premium}/{year}/{yearCode}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<AjaxResponse<ProductEntity>> getProduct(@PathVariable("gender") String gender,
			@PathVariable("bDate") String bDate, @PathVariable("currency") String currency1,
			@PathVariable("paymentMethod") String paymentMethod,
			@PathVariable("interestRateType") String interestRateType, @PathVariable("premium") String premium1,
			@PathVariable("year") String year, @PathVariable("yearCode") String yearCode) {
		Collection<AjaxResponse<ProductEntity>> responses = new ArrayList<>();

		Currency currency = null;
		if (currency1.equals("TWD")) {
			currency = Currency.TWD;
		} else if (currency1.equals("USD")) {
			currency = Currency.USD;
		} else if (currency1.equals("RMB")) {
			currency = Currency.RMB;
		} else if (currency1.equals("AUD")) {
			currency = Currency.AUD;
		}
		if (gender.equals("Male")) {
			gender = "M";
		} else {
			gender = "F";
		}
		int yearINT = productService.stringToInt(year);// 繳費年期
		if(paymentMethod.equals("once")){
			yearINT=1;
		}else if(paymentMethod.equals("many")){
			yearINT = productService.stringToInt(year);
		}
		int insAge = productService.bDateToInt(bDate);// 年齡
		int yearCodeInt = productService.stringToInt(yearCode);// 預計領回時間
		double costPerYear = Double.parseDouble(premium1);// 年繳多少錢
		if (interestRateType.equals("declareInterestRate")) {
			interestRateType = "宣告利率";
		} else {
			interestRateType = "預定利率";
		}
		System.out.println("yearINT= " + yearINT);
		System.out.println("insAge= " + insAge);
		System.out.println("gender= " + gender);
		List<ProductEntity> products = productService.search(gender, insAge, currency, interestRateType, yearINT);
		System.out.println("productsproductsproducts");
		for (ProductEntity product : products) {
			AjaxResponse<ProductEntity> response = new AjaxResponse<ProductEntity>();
			try {
				System.out.println("id = "+product.getId());
				double premiumRatio = productService.getPremiumRatio(product);
				System.out.println("premiumRatio= "+premiumRatio);
				BigDecimal insureAmount = BigDecimal.valueOf(costPerYear / premiumRatio);
				if (product.getCurr() == Currency.TWD) {
					insureAmount = insureAmount.setScale(0, BigDecimal.ROUND_HALF_UP);// 4捨5入到整數
					System.out.println("insureAmount= " + insureAmount);
				} else if (product.getCurr() == Currency.USD || product.getCurr() == Currency.AUD) {
					insureAmount = insureAmount.setScale(1, BigDecimal.ROUND_HALF_UP);// 4捨5入到小數點後一位
					System.out.println("insureAmount= " + insureAmount);
				} else if (product.getCurr() == Currency.RMB) {
					insureAmount = insureAmount.setScale(2, BigDecimal.ROUND_HALF_UP);// 4捨5入到小數點後二位
					System.out.println("insureAmount= " + insureAmount);
				}
				product.setInsureAmount(insureAmount);// 算出保額
				for (ProductHighDiscountRatio productHighDiscountRatio : product.getHighDiscountRatios()) {
					product.getHighDiscountRatios().iterator().next();
					double min = productHighDiscountRatio.getMinValue().doubleValue();
					double max = productHighDiscountRatio.getMaxValue();
					double percentOff = productHighDiscountRatio.getDiscountRatio().doubleValue();
					System.out.println("min= " + min);
					System.out.println("max= " + max);
					if (insureAmount.doubleValue() >= min && insureAmount.doubleValue() <= max) {
						product.setPremium(// 計算保費
								BigDecimal.valueOf(insureAmount.doubleValue() * premiumRatio * (1 - percentOff))
										.setScale(0, BigDecimal.ROUND_HALF_UP));
						System.out.println("premium= " + product.getPremium().doubleValue());
						break;
					}else{
						product.setPremium(null);
					}
				}
				double cancelRatio = productService.toCancelRatio(yearCodeInt, product).doubleValue()
						* insureAmount.doubleValue();
				System.out.println("cancelRatio= " + cancelRatio);
				product.setCashValue(BigDecimal.valueOf(cancelRatio));// 解約金

				double getPoint = product.getBonusPoint().doubleValue() * product.getPremium().doubleValue();
				product.setGetPoint(BigDecimal.valueOf(getPoint).setScale(0, BigDecimal.ROUND_DOWN));// 獲得點數
																										// 保費*點數趴數

				double period = (double) yearINT;
				double times = (double) yearCodeInt;
				double premium;
				if(product.getPremium()!=null){
					premium = product.getPremium().doubleValue();
				}
				double expired = product.getCashValue().doubleValue();
				if(product.getPremium()!=null){
					premium = product.getPremium().doubleValue();
					double irr = iRRCaculator.getIRR(period, times, premium, expired);
					product.setIrr(irr);					
					System.out.println("irr=" + irr);
				}

				if (product.getPremium() == null) {// 如果保額不在上下限範圍之內 則不顯示這張表單
					System.out.println("NO product.getPremium()!!!");
					continue;
				}else{
					response.setData(product);
					System.out.println(product);
				}
				log.debug("{}", response);
			} catch (final ApplicationException ex) {
				ex.printStackTrace();
				response.addMessages(ex.getMessages());
			} catch (final Exception e) {
				response.addException(e);
			}
			responses.add(response);
			
		}

		System.out.println(responses);

		return responses;
	}

}
