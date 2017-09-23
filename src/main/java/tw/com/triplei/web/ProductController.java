package tw.com.triplei.web;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.reflect.MethodUtils;
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
import tw.com.triplei.entity.ProductCancelRatio;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.ProductHighDiscountRatio;
import tw.com.triplei.entity.ProductPremiumRatio;
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

	@GetMapping("/{id}/{gender}/{bDate}/{premium}/{yearCode}")
	public String detailInfo(@PathVariable("id") String id,@PathVariable("gender") String gender,
			@PathVariable("bDate") String bDate,@PathVariable("premium") String premium,
			@PathVariable("yearCode") String yearCode, Model model) throws Exception {
//		long idLong = Integer.parseInt(id);// 抓那個商品的ID
//		int age = productService.bDateToInt(bDate);
//		int premiumPerYear = Integer.parseInt(premium);
//		int yearMoneyBack = Integer.parseInt(yearCode);
//		ProductEntity form = productService.findProduct(idLong);
//		while(form.getPremiumRatios().iterator().hasNext()){
//			ProductPremiumRatio productPremiumRatioff = form.getPremiumRatios().iterator().next();
//			if(productPremiumRatioff.getInsAge()==age && productPremiumRatioff.getGender().equals(gender)){
//				premiumPerYear/productPremiumRatioff.getPremiumRatio().doubleValue();//保費
//			}
//		}
//		log.debug("form : ", form);
//		model.addAttribute("modelf", form);
//		List<double[]> totalCancelRatio = new ArrayList<>();
//		List<double[]> totalIrrAndCancelRatio = new ArrayList<>();
//		ProductEntity product = productService.findProduct(idLong);
//		log.debug("商品 : ", product);
//		ProductCancelRatio productCancelRatiof = form.getCancelRatios().iterator().next();
//		while (product.getCancelRatios().iterator().hasNext()) {
//			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
//			if (productCancelRatio.getGender().equals(form.getCancelRatios().iterator().next().getGender())
//					&& productCancelRatio.getInsAge() == productCancelRatiof.getInsAge()) {
//
//				log.debug("性別 : ", productCancelRatio.getGender());
//				log.debug("投保年齡 : ", productCancelRatio.getInsAge());
//				for (int i = 0; i <= 111; i++) {
//					MethodUtils methodUtils = new MethodUtils();
//					double cancelRatio = form.getInsureAmount().doubleValue()// 每年解約金
//							* ((BigDecimal) methodUtils.invokeMethod(productCancelRatio, "getCancelRatio_" + i, null))
//									.doubleValue();
//					log.debug("每年解約金={}", cancelRatio);
//
//					if (i <= product.getYear()) {// [年,共繳多少保費,解約可獲得保費,irr]
//
//						double[] data = { (double) i, i * form.getPremiumAfterDiscount().doubleValue(), cancelRatio,
//								iRRCaculator.getIRR((double) form.getYear(), (double) i,
//										form.getPremiumAfterDiscount().doubleValue(), cancelRatio) };
//						totalCancelRatio.add(data);
//
//						double[] irrAndCancelRatio = { cancelRatio, iRRCaculator.getIRR((double) form.getYear(),
//								(double) i, form.getPremiumAfterDiscount().doubleValue(), cancelRatio) };
//						totalIrrAndCancelRatio.add(irrAndCancelRatio);
//						log.debug("每年={}", data);
//					} else {
//						double[] data = { (double) i, product.getYear() * form.getPremiumAfterDiscount().doubleValue(),
//								cancelRatio, iRRCaculator.getIRR((double) form.getYear(), (double) i,
//										form.getPremiumAfterDiscount().doubleValue(), cancelRatio) };
//						totalCancelRatio.add(data);
//
//						double[] irrAndCancelRatio = { cancelRatio, iRRCaculator.getIRR((double) form.getYear(),
//								(double) i, form.getPremiumAfterDiscount().doubleValue(), cancelRatio) };
//						totalIrrAndCancelRatio.add(irrAndCancelRatio);
//						log.debug("每年={}", data);
//					}
//
//				}
//				model.addAttribute("totalCancelRatio", totalCancelRatio);
//				model.addAttribute("totalIrrAndCancelRatio", totalIrrAndCancelRatio);
//				log.debug("total資料={}", totalCancelRatio);
//			}
//		}
		return "/product/detailInfo";
	}

	@GetMapping("/getYear")
	@ResponseBody
	public Collection<Integer> getYear() {
		List<ProductEntity> products = productService.getAll();
		Collection<Integer> years = new ArrayList<>();
		for (ProductEntity product : products) {
			int year = product.getYear();
			if (!years.contains(year)) {
				years.add(year);
			}
		}
		log.debug("year{}", years);
		return years;
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
		String j = new BigDecimal(i).multiply(new BigDecimal("100")).toString();
		String irr = String.valueOf(j).substring(0, String.valueOf(j).indexOf(".") + 3); // 先乘100後取小數點後兩位
		log.debug("irr{}", irr);

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

	@GetMapping("/Adjustment/{gender}/{bDate}/{insureAmount}/{id}/{yearCode}")
	@ResponseBody
	public String insureAmountAndYearAndGenderAdjustment(@PathVariable("gender") String gender,
			@PathVariable("bDate") String bDate, @PathVariable("insureAmount") String insureAmountS,
			@PathVariable("id") String idS, @PathVariable("yearCode") String yearCode,Model model)//這裡yearCode指的是 領回時間
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		List<double[]> totalForm = new ArrayList<>();
		List<double[]> totalIrrAndCancelRatio = new ArrayList<>();
		long id = Integer.parseInt(idS);
		log.debug("id{}", id);
		BigDecimal insureAmount = BigDecimal.valueOf(Double.parseDouble(insureAmountS));
		log.debug("insureAmount{}", insureAmount);
		int age = productService.bDateToInt(bDate);
		log.debug("age{}", age);
		int yearMoneyBack = Integer.parseInt(yearCode);
		log.debug("yearMoneyBack{}", yearMoneyBack);
		ProductEntity product = productService.getOneAll(id);
		log.debug("product{}", product);
		product.setInsureAmount(insureAmount);
		while (product.getPremiumRatios().iterator().hasNext()) {
			ProductPremiumRatio productPremiumRatio = product.getPremiumRatios().iterator().next();
			if (productPremiumRatio.getInsAge() == age && productPremiumRatio.getGender().equals(gender)) {
				log.debug("age{}", age);
				log.debug("productPremiumRatio.getInsAge(){}", productPremiumRatio.getInsAge());
				log.debug("gender{}", gender);
				log.debug("productPremiumRatio.getGender(){}", productPremiumRatio.getGender());
				while (product.getHighDiscountRatios().iterator().hasNext()) {
					ProductHighDiscountRatio productHighDiscountRatio = product.getHighDiscountRatios().iterator()
							.next();
					if (insureAmount.doubleValue() >= productHighDiscountRatio.getMinValue().doubleValue()
							&& insureAmount.doubleValue() <= productHighDiscountRatio.getMaxValue().doubleValue()) {
						// 保費
						product.setPremium(BigDecimal.valueOf(
								insureAmount.doubleValue() * productPremiumRatio.getPremiumRatio().doubleValue()));
						// 折扣後保費
						product.setPremiumAfterDiscount(BigDecimal.valueOf(
								insureAmount.doubleValue() * productPremiumRatio.getPremiumRatio().doubleValue()
										* (1-productHighDiscountRatio.getDiscountRatio().doubleValue())));
						// 折扣趴數
						product.setDiscount(productHighDiscountRatio.getDiscountRatio());
						// 可獲點數
						product.setGetPoint(BigDecimal.valueOf(
								insureAmount.doubleValue() * productPremiumRatio.getPremiumRatio().doubleValue()
										* (1-productHighDiscountRatio.getDiscountRatio().doubleValue())
										* product.getBonusPoint().doubleValue()).setScale(0,BigDecimal.ROUND_FLOOR));

						while (product.getCancelRatios().iterator().hasNext()) {
							ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
							if (productCancelRatio.getInsAge() == age
									&& productCancelRatio.getGender().equals(gender)) {
								for(int i=0;i<=111;i++){
									BigDecimal cancelRatio = (BigDecimal)MethodUtils.invokeMethod(productCancelRatio, "getCancelRatio_"+i,null);
									//各年解約金
									double cancelRatioPerYear = cancelRatio.doubleValue() * insureAmount.doubleValue();
									//IRR
									double irr = irrCaculator.getIRR(product.getYear(), yearMoneyBack,
											insureAmount.doubleValue() * productPremiumRatio.getPremiumRatio().doubleValue()
											* (1-productHighDiscountRatio.getDiscountRatio().doubleValue()),
											cancelRatio.doubleValue());
									double[] irrAndCancelRatio = {cancelRatioPerYear,irr};
									totalIrrAndCancelRatio.add(irrAndCancelRatio);
									
									//各年總繳金額
									if(i<=product.getYear()){
										double PremiumAfterDiscountPerYear = product.getPremiumAfterDiscount().doubleValue() * i;
										double[] form = {(double)i,PremiumAfterDiscountPerYear,cancelRatioPerYear,irr};
										totalForm.add(form);
									}else{
										double PremiumAfterDiscountPerYear = product.getPremiumAfterDiscount().doubleValue() * product.getYear();
										double[] form = {(double)i,PremiumAfterDiscountPerYear,cancelRatioPerYear,irr};
										totalForm.add(form);
									}
								}						
								BigDecimal cancelRatio = (BigDecimal) MethodUtils.invokeMethod(productCancelRatio,
										"getCancelRatio_" + yearMoneyBack, null);
								// 違約金
								product.setCashValue(
										BigDecimal.valueOf(cancelRatio.doubleValue() * insureAmount.doubleValue()));
								double irr = irrCaculator.getIRR(product.getYear(), yearMoneyBack,
										insureAmount.doubleValue() * productPremiumRatio.getPremiumRatio().doubleValue()
												* (1-productHighDiscountRatio.getDiscountRatio().doubleValue()),
										cancelRatio.doubleValue());
								// IRR
								product.setIrr(irr);
								log.debug("irr{}", irr);
							}
						}
						break;
					} else {
						product.setDiscount(null);
					}
				}
				if (product.getDiscount() == null) {
					product = new ProductEntity();
					break;
				}

			}

		}
		model.addAttribute("product",product);
		model.addAttribute("totalForm",totalForm);
		model.addAttribute("totalIrrAndCancelRatio",totalIrrAndCancelRatio);
		
		return "Redirect:/product/detailInfo";
	}

	@RequestMapping(value = "getProduct/{gender}/{bDate}/{currency}/{paymentMethod}/{interestRateType}/{premium}/{year}/{yearCode}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<ProductEntity> getProduct(@PathVariable("gender") String gender,
			@PathVariable("bDate") String bDate, @PathVariable("currency") String currency1,
			@PathVariable("paymentMethod") String paymentMethod,
			@PathVariable("interestRateType") String interestRateType, @PathVariable("premium") String premium1,
			@PathVariable("year") String year, @PathVariable("yearCode") String yearCode) {
		Collection<ProductEntity> productss = new ArrayList<>();

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
		if (paymentMethod.equals("once")) {
			yearINT = 1;
		} else if (paymentMethod.equals("many")) {
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
		for (ProductEntity product : products) {
			try {
				System.out.println("id = " + product.getId());
				double premiumRatio = product.getPremiumRatios().iterator().next().getPremiumRatio().doubleValue();
//				double premiumRatio = productService.getPremiumRatio(product);
				System.out.println("premiumRatio= " + premiumRatio);
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
						product.setPremium(// 計算原本保費
								BigDecimal.valueOf(insureAmount.doubleValue() * premiumRatio).setScale(0,
										BigDecimal.ROUND_HALF_UP));
						product.setPremiumAfterDiscount(// 計算折扣後保費
								BigDecimal.valueOf(insureAmount.doubleValue() * premiumRatio * (1 - percentOff))
										.setScale(0, BigDecimal.ROUND_HALF_UP));
						product.setDiscount(BigDecimal.valueOf(percentOff));
						if (yearCodeInt >= yearINT) {
							product.setTotalPay(BigDecimal
									.valueOf(insureAmount.doubleValue() * premiumRatio * (1 - percentOff) * yearINT));
						} else {
							product.setTotalPay(BigDecimal.valueOf(
									insureAmount.doubleValue() * premiumRatio * (1 - percentOff) * yearCodeInt));
						}
						System.out.println("premium= " + product.getPremium().doubleValue());
						log.debug("總額:{}", product.getTotalPay().doubleValue());
						break;
					} else {
						product.setPremium(null);
					}
				}
//				double cancelRatio = productService.toCancelRatio(yearCodeInt, product).doubleValue()
//						* insureAmount.doubleValue();
				ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
				
				MethodUtils methodUtils = new MethodUtils();
				BigDecimal cancelRatiob = (BigDecimal) methodUtils.invokeMethod(productCancelRatio, "getCancelRatio_" + yearCodeInt, null);
				
				double cancelRatio = cancelRatiob.doubleValue() * insureAmount.doubleValue();
				System.out.println("cancelRatio= " + cancelRatio);
				
				product.setCashValue(BigDecimal.valueOf(cancelRatio));// 解約金
				product.setNet(BigDecimal.valueOf(cancelRatio - product.getTotalPay().doubleValue()));// 淨賺
				log.debug("淨賺:{}", cancelRatio - product.getTotalPay().doubleValue());
				double getPoint = product.getBonusPoint().doubleValue() * product.getPremium().doubleValue();
				product.setGetPoint(BigDecimal.valueOf(getPoint).setScale(0, BigDecimal.ROUND_DOWN));// 獲得點數
																										// 保費*點數趴數

				double period = (double) yearINT;
				double times = (double) yearCodeInt;
				double premium;
				if (product.getPremium() != null) {
					premium = product.getPremium().doubleValue();
				}
				double expired = product.getCashValue().doubleValue();
				if (product.getPremium() != null) {
					premium = product.getPremium().doubleValue();
					double irr = iRRCaculator.getIRR(period, times, premium, expired);
					product.setIrr(irr);
					System.out.println("irr=" + irr);
				}

				if (product.getPremium() == null) {// 如果保額不在上下限範圍之內 則不顯示這張表單
					System.out.println("NO product.getPremium()!!!");
					continue;
				} else {
					productss.add(product);
					System.out.println(productss);
				}
			} catch (final ApplicationException ex) {
				ex.printStackTrace();
			} catch (final Exception e) {
				e.printStackTrace();
			}

		}
		return productss;
	}

}
