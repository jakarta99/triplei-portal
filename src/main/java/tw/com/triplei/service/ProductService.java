package tw.com.triplei.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.InsurerDao;
import tw.com.triplei.dao.ProductCancelRatioDao;
import tw.com.triplei.dao.ProductDao;
import tw.com.triplei.dao.ProductHighDiscountRatioDao;
import tw.com.triplei.dao.ProductPremiumRatioDao;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.entity.ProductCancelRatio;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.ProductHighDiscountRatio;
import tw.com.triplei.entity.ProductPremiumRatio;
import tw.com.triplei.enums.Currency;

@Slf4j
@Service
public class ProductService extends GenericService<ProductEntity> {

	@Autowired
	private ProductDao dao;

	@Autowired
	private InsurerDao insurerDao;

	@Autowired
	private ProductPremiumRatioDao productPremiumRatioDao;

	@Autowired
	private ProductCancelRatioDao productCancelRatioDao;

	@Autowired
	private ProductHighDiscountRatioDao productHighDiscountRatioDao;

	// @Autowired
	// private Currency currency;

	private BigDecimal bigDecimal;

	public ProductPremiumRatioDao getProductPremiumRatioDao() {
		return productPremiumRatioDao;
	}

	public ProductCancelRatioDao getProductCancelRatioDao() {
		return productCancelRatioDao;
	}

	public ProductHighDiscountRatioDao getProductHighDiscountRatioDao() {
		return productHighDiscountRatioDao;
	}

	public int stringToAge(String string) {
		int number = Integer.parseInt(string);
		return number;
	}

	// public Currency stringToCurrency(String curr){
	// if(curr=="AUD"){
	// return currency.AUD;
	// }else if(curr=="USD"){
	// return currency.USD;
	// }else if (curr=="RMB"){
	// return currency.RMB;
	// }else if(curr=="TWD"){
	// return currency.TWD;
	// }
	// return currency;
	// }

	public int bDateToInt(String bDate) {
		String[] bDates = bDate.split("-");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(date);
		String[] nowDates = nowDate.split("-");
		int bDate0 = Integer.parseInt(bDates[0]);
		int bDate1 = Integer.parseInt(bDates[1]);
		int bDate2 = Integer.parseInt(bDates[2]);
		int nowDate0 = Integer.parseInt(nowDates[0]);
		int nowDate1 = Integer.parseInt(nowDates[1]);
		int nowDate2 = Integer.parseInt(nowDates[2]);
		if (nowDate0 - bDate0 < 0) { // 年份相減 未來人年紀為-1
			return -1;
		} else if (nowDate0 - bDate0 == 0) {// 同年出生
			if (nowDate1 - bDate1 < 0) { // 同年出生，月份比現在大，未來人
				return -1;
			} else if (nowDate1 - bDate1 > 6) {// 同年出生，出生超過6個月，年齡算1歲
				return nowDate0 - bDate0 + 1;
			} else if (nowDate1 - bDate1 == 6) {// 同年出生，差6個月，比較日期
				if (nowDate2 - bDate2 <= 0) {// 不足6個月
					return nowDate0 - bDate0;
				} else {// 滿6個月又1天 +1歲
					return nowDate0 - bDate0 + 1;
				}
			} else if (nowDate1 - bDate1 == 0) {// 同月份 比較日期
				if (nowDate2 - bDate2 < 0) {
					return -1;// 未來人
				} else {
					return nowDate0 - bDate0;
				}
			} else {// 相差6個月內
				return nowDate0 - bDate0;
			}
		} else { // 已出生
			if (nowDate1 - bDate1 < 6 && nowDate1 - bDate1 > -6) {
				return nowDate0 - bDate0;
			} else if (nowDate1 - bDate1 < -6) {
				return nowDate0 - bDate0 - 1;
			} else if (nowDate1 - bDate1 > 6) {// 滿6個月，歲數+1
				return nowDate0 - bDate0 + 1;
			} else if (nowDate1 - bDate1 == 6) {
				if (nowDate2 - bDate2 <= 0) {
					return nowDate0 - bDate0;
				} else {
					return nowDate0 - bDate0 + 1;
				}
			} else {
				if (nowDate2 - bDate2 <= 0) {
					return nowDate0 - bDate0 - 1;
				} else {
					return nowDate0 - bDate0;
				}
			}
		}
	}

	// public List<ProductEntity> search(String gender,int insAge,Currency
	// currency,String interestRateType,int year){
	// return dao.search(gender, insAge, currency, interestRateType, year);
	// }

	public double getPremiumRatio(ProductEntity product) {
		double premiumRatio = 0;
		try {
			log.debug("{}",product);
			premiumRatio = product.getPremiumRatios().iterator().next().getPremiumRatio().doubleValue();
		} catch (Exception e) {
			System.out.println("NOOOO");
			e.printStackTrace();
		}
		return premiumRatio;
	}

	public List<ProductEntity> search(String gender, int insAge, Currency currency, String interestRateType, int year) {
		
		List<ProductEntity> products = dao.findByCurrAndInterestRateTypeAndYear(currency, interestRateType, year);
		log.debug("products{}",products);
		List<ProductEntity> productss = new ArrayList<>();
		for (ProductEntity product : products) {
			log.debug("productID{},Age{},Gender{}",product.getId(),insAge,gender);
			product.setPremiumRatios(
					productPremiumRatioDao.findByProductIdAndInsAgeAndGender(product.getId(), insAge, gender));
			product.setHighDiscountRatios(productHighDiscountRatioDao.findByProductId(product.getId()));
			product.setCancelRatios(
					productCancelRatioDao.findByProductIdAndInsAgeAndGender(product.getId(), insAge, gender));
			productss.add(product);
		}
		System.out.println("productss" + productss);
		return productss;
	}

	public BigDecimal toCancelRatio(int yearCode, ProductEntity product) {
		if (yearCode == 0) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_0() != null) {
				return productCancelRatio.getCancelRatio_0();
			} else {
				return null;
			}
		} else if (yearCode == 1) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_1() != null) {
				return productCancelRatio.getCancelRatio_1();
			} else {
				return null;
			}
		} else if (yearCode == 2) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_2() != null) {
				return productCancelRatio.getCancelRatio_2();
			} else {
				return null;
			}
		} else if (yearCode == 3) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_3() != null) {
				return productCancelRatio.getCancelRatio_3();
			} else {
				return null;
			}
		} else if (yearCode == 4) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_4() != null) {
				return productCancelRatio.getCancelRatio_4();
			} else {
				return null;
			}
		} else if (yearCode == 5) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_5() != null) {
				return productCancelRatio.getCancelRatio_5();
			} else {
				return null;
			}
		} else if (yearCode == 6) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_6() != null) {
				return productCancelRatio.getCancelRatio_6();
			} else {
				return null;
			}
		} else if (yearCode == 7) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_7() != null) {
				return productCancelRatio.getCancelRatio_7();
			} else {
				return null;
			}
		} else if (yearCode == 8) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_8() != null) {
				return productCancelRatio.getCancelRatio_8();
			} else {
				return null;
			}
		} else if (yearCode == 9) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_9() != null) {
				return productCancelRatio.getCancelRatio_9();
			} else {
				return null;
			}
		} else if (yearCode == 10) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_10() != null) {
				return productCancelRatio.getCancelRatio_10();
			} else {
				return null;
			}
		} else if (yearCode == 11) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_11() != null) {
				return productCancelRatio.getCancelRatio_11();
			} else {
				return null;
			}
		} else if (yearCode == 12) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_12() != null) {
				return productCancelRatio.getCancelRatio_12();
			} else {
				return null;
			}
		} else if (yearCode == 13) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_13() != null) {
				return productCancelRatio.getCancelRatio_13();
			} else {
				return null;
			}
		} else if (yearCode == 14) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_14() != null) {
				return productCancelRatio.getCancelRatio_14();
			} else {
				return null;
			}
		} else if (yearCode == 15) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_15() != null) {
				return productCancelRatio.getCancelRatio_15();
			} else {
				return null;
			}
		} else if (yearCode == 16) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_16() != null) {
				return productCancelRatio.getCancelRatio_16();
			} else {
				return null;
			}
		} else if (yearCode == 17) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_17() != null) {
				return productCancelRatio.getCancelRatio_17();
			} else {
				return null;
			}
		} else if (yearCode == 18) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_18() != null) {
				return productCancelRatio.getCancelRatio_18();
			} else {
				return null;
			}
		} else if (yearCode == 19) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_19() != null) {
				return productCancelRatio.getCancelRatio_19();
			} else {
				return null;
			}
		} else if (yearCode == 20) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_20() != null) {
				return productCancelRatio.getCancelRatio_20();
			} else {
				return null;
			}
		} else if (yearCode == 21) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_21() != null) {
				return productCancelRatio.getCancelRatio_21();
			} else {
				return null;
			}
		} else if (yearCode == 22) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_22() != null) {
				return productCancelRatio.getCancelRatio_22();
			} else {
				return null;
			}
		} else if (yearCode == 23) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_23() != null) {
				return productCancelRatio.getCancelRatio_23();
			} else {
				return null;
			}
		} else if (yearCode == 24) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_24() != null) {
				return productCancelRatio.getCancelRatio_24();
			} else {
				return null;
			}
		} else if (yearCode == 25) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_25() != null) {
				return productCancelRatio.getCancelRatio_25();
			} else {
				return null;
			}
		} else if (yearCode == 26) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_26() != null) {
				return productCancelRatio.getCancelRatio_26();
			} else {
				return null;
			}
		} else if (yearCode == 27) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_27() != null) {
				return productCancelRatio.getCancelRatio_27();
			} else {
				return null;
			}
		} else if (yearCode == 28) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_28() != null) {
				return productCancelRatio.getCancelRatio_28();
			} else {
				return null;
			}
		} else if (yearCode == 29) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_29() != null) {
				return productCancelRatio.getCancelRatio_29();
			} else {
				return null;
			}
		} else if (yearCode == 30) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_30() != null) {
				return productCancelRatio.getCancelRatio_30();
			} else {
				return null;
			}
		} else if (yearCode == 31) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_31() != null) {
				return productCancelRatio.getCancelRatio_31();
			} else {
				return null;
			}
		} else if (yearCode == 32) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_32() != null) {
				return productCancelRatio.getCancelRatio_32();
			} else {
				return null;
			}
		} else if (yearCode == 33) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_33() != null) {
				return productCancelRatio.getCancelRatio_33();
			} else {
				return null;
			}
		} else if (yearCode == 34) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_34() != null) {
				return productCancelRatio.getCancelRatio_34();
			} else {
				return null;
			}
		} else if (yearCode == 35) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_35() != null) {
				return productCancelRatio.getCancelRatio_35();
			} else {
				return null;
			}
		} else if (yearCode == 36) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_36() != null) {
				return productCancelRatio.getCancelRatio_36();
			} else {
				return null;
			}
		} else if (yearCode == 37) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_37() != null) {
				return productCancelRatio.getCancelRatio_37();
			} else {
				return null;
			}
		} else if (yearCode == 38) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_38() != null) {
				return productCancelRatio.getCancelRatio_38();
			} else {
				return null;
			}
		} else if (yearCode == 39) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_39() != null) {
				return productCancelRatio.getCancelRatio_39();
			} else {
				return null;
			}
		} else if (yearCode == 40) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_40() != null) {
				return productCancelRatio.getCancelRatio_40();
			} else {
				return null;
			}
		} else if (yearCode == 41) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_41() != null) {
				return productCancelRatio.getCancelRatio_41();
			} else {
				return null;
			}
		} else if (yearCode == 42) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_42() != null) {
				return productCancelRatio.getCancelRatio_42();
			} else {
				return null;
			}
		} else if (yearCode == 43) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_43() != null) {
				return productCancelRatio.getCancelRatio_43();
			} else {
				return null;
			}
		} else if (yearCode == 44) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_44() != null) {
				return productCancelRatio.getCancelRatio_44();
			} else {
				return null;
			}
		} else if (yearCode == 45) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_45() != null) {
				return productCancelRatio.getCancelRatio_45();
			} else {
				return null;
			}
		} else if (yearCode == 46) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_46() != null) {
				return productCancelRatio.getCancelRatio_46();
			} else {
				return null;
			}
		} else if (yearCode == 47) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_47() != null) {
				return productCancelRatio.getCancelRatio_47();
			} else {
				return null;
			}
		} else if (yearCode == 48) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_48() != null) {
				return productCancelRatio.getCancelRatio_48();
			} else {
				return null;
			}
		} else if (yearCode == 49) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_49() != null) {
				return productCancelRatio.getCancelRatio_49();
			} else {
				return null;
			}
		} else if (yearCode == 50) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_50() != null) {
				return productCancelRatio.getCancelRatio_50();
			} else {
				return null;
			}
		} else if (yearCode == 51) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_51() != null) {
				return productCancelRatio.getCancelRatio_51();
			} else {
				return null;
			}
		} else if (yearCode == 52) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_52() != null) {
				return productCancelRatio.getCancelRatio_52();
			} else {
				return null;
			}
		} else if (yearCode == 53) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_53() != null) {
				return productCancelRatio.getCancelRatio_53();
			} else {
				return null;
			}
		} else if (yearCode == 54) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_54() != null) {
				return productCancelRatio.getCancelRatio_54();
			} else {
				return null;
			}
		} else if (yearCode == 55) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_55() != null) {
				return productCancelRatio.getCancelRatio_55();
			} else {
				return null;
			}
		} else if (yearCode == 56) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_56() != null) {
				return productCancelRatio.getCancelRatio_56();
			} else {
				return null;
			}
		} else if (yearCode == 57) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_57() != null) {
				return productCancelRatio.getCancelRatio_57();
			} else {
				return null;
			}
		} else if (yearCode == 58) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_58() != null) {
				return productCancelRatio.getCancelRatio_58();
			} else {
				return null;
			}
		} else if (yearCode == 59) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_59() != null) {
				return productCancelRatio.getCancelRatio_59();
			} else {
				return null;
			}
		} else if (yearCode == 60) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_60() != null) {
				return productCancelRatio.getCancelRatio_60();
			} else {
				return null;
			}
		} else if (yearCode == 61) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_61() != null) {
				return productCancelRatio.getCancelRatio_61();
			} else {
				return null;
			}
		} else if (yearCode == 62) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_62() != null) {
				return productCancelRatio.getCancelRatio_62();
			} else {
				return null;
			}
		} else if (yearCode == 63) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_63() != null) {
				return productCancelRatio.getCancelRatio_63();
			} else {
				return null;
			}
		} else if (yearCode == 64) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_64() != null) {
				return productCancelRatio.getCancelRatio_64();
			} else {
				return null;
			}
		} else if (yearCode == 65) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_65() != null) {
				return productCancelRatio.getCancelRatio_65();
			} else {
				return null;
			}
		} else if (yearCode == 66) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_66() != null) {
				return productCancelRatio.getCancelRatio_66();
			} else {
				return null;
			}
		} else if (yearCode == 67) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_67() != null) {
				return productCancelRatio.getCancelRatio_67();
			} else {
				return null;
			}
		} else if (yearCode == 68) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_68() != null) {
				return productCancelRatio.getCancelRatio_68();
			} else {
				return null;
			}
		} else if (yearCode == 69) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_69() != null) {
				return productCancelRatio.getCancelRatio_69();
			} else {
				return null;
			}
		} else if (yearCode == 70) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_70() != null) {
				return productCancelRatio.getCancelRatio_70();
			} else {
				return null;
			}
		} else if (yearCode == 71) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_71() != null) {
				return productCancelRatio.getCancelRatio_71();
			} else {
				return null;
			}
		} else if (yearCode == 72) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_72() != null) {
				return productCancelRatio.getCancelRatio_72();
			} else {
				return null;
			}
		} else if (yearCode == 73) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_73() != null) {
				return productCancelRatio.getCancelRatio_73();
			} else {
				return null;
			}
		} else if (yearCode == 74) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_74() != null) {
				return productCancelRatio.getCancelRatio_74();
			} else {
				return null;
			}
		} else if (yearCode == 75) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_75() != null) {
				return productCancelRatio.getCancelRatio_75();
			} else {
				return null;
			}
		} else if (yearCode == 76) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_76() != null) {
				return productCancelRatio.getCancelRatio_76();
			} else {
				return null;
			}
		} else if (yearCode == 77) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_77() != null) {
				return productCancelRatio.getCancelRatio_77();
			} else {
				return null;
			}
		} else if (yearCode == 78) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_78() != null) {
				return productCancelRatio.getCancelRatio_78();
			} else {
				return null;
			}
		} else if (yearCode == 79) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_79() != null) {
				return productCancelRatio.getCancelRatio_79();
			} else {
				return null;
			}
		} else if (yearCode == 80) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_80() != null) {
				return productCancelRatio.getCancelRatio_80();
			} else {
				return null;
			}
		} else if (yearCode == 81) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_81() != null) {
				return productCancelRatio.getCancelRatio_81();
			} else {
				return null;
			}
		} else if (yearCode == 82) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_82() != null) {
				return productCancelRatio.getCancelRatio_82();
			} else {
				return null;
			}
		} else if (yearCode == 83) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_83() != null) {
				return productCancelRatio.getCancelRatio_83();
			} else {
				return null;
			}
		} else if (yearCode == 84) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_84() != null) {
				return productCancelRatio.getCancelRatio_84();
			} else {
				return null;
			}
		} else if (yearCode == 85) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_85() != null) {
				return productCancelRatio.getCancelRatio_85();
			} else {
				return null;
			}
		} else if (yearCode == 86) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_86() != null) {
				return productCancelRatio.getCancelRatio_86();
			} else {
				return null;
			}
		} else if (yearCode == 87) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_87() != null) {
				return productCancelRatio.getCancelRatio_87();
			} else {
				return null;
			}
		} else if (yearCode == 88) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_88() != null) {
				return productCancelRatio.getCancelRatio_88();
			} else {
				return null;
			}
		} else if (yearCode == 89) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_89() != null) {
				return productCancelRatio.getCancelRatio_89();
			} else {
				return null;
			}
		} else if (yearCode == 90) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_90() != null) {
				return productCancelRatio.getCancelRatio_90();
			} else {
				return null;
			}
		} else if (yearCode == 91) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_91() != null) {
				return productCancelRatio.getCancelRatio_91();
			} else {
				return null;
			}
		} else if (yearCode == 92) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_92() != null) {
				return productCancelRatio.getCancelRatio_92();
			} else {
				return null;
			}
		} else if (yearCode == 93) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_93() != null) {
				return productCancelRatio.getCancelRatio_93();
			} else {
				return null;
			}
		} else if (yearCode == 94) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_94() != null) {
				return productCancelRatio.getCancelRatio_94();
			} else {
				return null;
			}
		} else if (yearCode == 95) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_95() != null) {
				return productCancelRatio.getCancelRatio_95();
			} else {
				return null;
			}
		} else if (yearCode == 96) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_96() != null) {
				return productCancelRatio.getCancelRatio_96();
			} else {
				return null;
			}
		} else if (yearCode == 97) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_97() != null) {
				return productCancelRatio.getCancelRatio_97();
			} else {
				return null;
			}
		} else if (yearCode == 98) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_98() != null) {
				return productCancelRatio.getCancelRatio_98();
			} else {
				return null;
			}
		} else if (yearCode == 99) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_99() != null) {
				return productCancelRatio.getCancelRatio_99();
			} else {
				return null;
			}
		} else if (yearCode == 100) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_100() != null) {
				return productCancelRatio.getCancelRatio_100();
			} else {
				return null;
			}
		} else if (yearCode == 101) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_101() != null) {
				return productCancelRatio.getCancelRatio_101();
			} else {
				return null;
			}
		} else if (yearCode == 102) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_102() != null) {
				return productCancelRatio.getCancelRatio_102();
			} else {
				return null;
			}
		} else if (yearCode == 103) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_103() != null) {
				return productCancelRatio.getCancelRatio_103();
			} else {
				return null;
			}
		} else if (yearCode == 104) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_104() != null) {
				return productCancelRatio.getCancelRatio_104();
			} else {
				return null;
			}
		} else if (yearCode == 105) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_105() != null) {
				return productCancelRatio.getCancelRatio_105();
			} else {
				return null;
			}
		} else if (yearCode == 106) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_106() != null) {
				return productCancelRatio.getCancelRatio_106();
			} else {
				return null;
			}
		} else if (yearCode == 107) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_107() != null) {
				return productCancelRatio.getCancelRatio_107();
			} else {
				return null;
			}
		} else if (yearCode == 108) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_108() != null) {
				return productCancelRatio.getCancelRatio_108();
			} else {
				return null;
			}
		} else if (yearCode == 109) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_109() != null) {
				return productCancelRatio.getCancelRatio_109();
			} else {
				return null;
			}
		} else if (yearCode == 110) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_110() != null) {
				return productCancelRatio.getCancelRatio_110();
			} else {
				return null;
			}
		} else if (yearCode == 111) {
			ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
			if (productCancelRatio.getCancelRatio_111() != null) {
				return productCancelRatio.getCancelRatio_111();
			} else {
				return null;
			}
		} else
			return null;
	}

	public int stringToInt(String string) {
		int number = Integer.parseInt(string);
		return number;
	}

	public BigDecimal stringToBigDecimal(String number) {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		try {
			bigDecimal = (BigDecimal) decimalFormat.parse(number);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		return bigDecimal;
	}

	private String url;

	@Override
	public GenericDao<ProductEntity> getDao() {
		return dao;
	}

	// @Autowired
	// private MultipartFile[] files;

	@Override
	public List<Message> validateInsert(ProductEntity entity) {

		List<Message> messages = Lists.newArrayList();

		ProductEntity dbEntity = dao.findById(entity.getId());

		if (dbEntity != null) {
			messages.add(Message.builder().code("code").value("ID不可重複").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(ProductEntity entity) {

		List<Message> messages = Lists.newArrayList();

		ProductEntity dbEntity = dao.findOne(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public ProductEntity handleUpdate(ProductEntity entity) {
		ProductEntity dbEntity = dao.findOne(entity.getId());

		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[] { "id", "code", "createdBy", "createdTime" });

		return dbEntity;
	}

	public boolean productUpload(MultipartFile file) throws Exception {
		BufferedOutputStream stream = null;
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String path = "src/main/resources/files";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			String date = DateTimeFormatter.ofPattern("MM-dd_HHmmss").format(LocalDateTime.now());
			File serverFile = new File(dir.getAbsolutePath() + File.separator + date + file.getOriginalFilename());
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			url = path + "/" + date + file.getOriginalFilename();
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertXlsxToDB(MultipartFile file) throws Exception {
		org.apache.poi.ss.usermodel.Sheet sheet;
		org.apache.poi.ss.usermodel.Workbook wb = null;
		try {
			if (url.endsWith(".xlsx")) {
				wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook(new File(url));
			} else if (url.endsWith(".xls")) {
				POIFSFileSystem fs = new POIFSFileSystem(new File(url));
				wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook(fs);
			}

			sheet = wb.getSheetAt(0);// 取得第一頁的資料

			// 從第3排開始往下讀
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				Row rowi = sheet.getRow(i);
				if (rowi.getCell(0).getStringCellValue() == "") {
					continue;
				}

				Collection<ProductPremiumRatio> productPremiumRatioList = new ArrayList<ProductPremiumRatio>();
				Collection<ProductHighDiscountRatio> productHighDiscountRatioList = new ArrayList<ProductHighDiscountRatio>();
				Collection<ProductCancelRatio> productCancelRatioList = new ArrayList<ProductCancelRatio>();

				ProductEntity product = new ProductEntity();

				// 保險公司
				String insurerName = rowi.getCell(0).getStringCellValue();
				InsurerEntity insurer = insurerDao.findByShortName(insurerName);
				product.setInsurer(insurer);
				// 商品名稱
				product.setLocalName(rowi.getCell(1).getStringCellValue());
				// 年期
				product.setYear((int) rowi.getCell(2).getNumericCellValue());
				// 商品年度
				product.setYearCode((int) rowi.getCell(3).getNumericCellValue() + "");
				// 商品代碼
				product.setCode(rowi.getCell(4).getStringCellValue());
				// 幣別
				if ("美元".equals(rowi.getCell(5).getStringCellValue())) {
					product.setCurr(Currency.USD);
				} else if ("新台幣".equals(rowi.getCell(5).getStringCellValue())) {
					product.setCurr(Currency.TWD);
				} else if ("人民幣".equals(rowi.getCell(5).getStringCellValue())) {
					product.setCurr(Currency.RMB);
				} else if ("澳幣".equals(rowi.getCell(5).getStringCellValue())) {
					product.setCurr(Currency.AUD);
				}

				// 預定利率
				try {
					product.setPredictInterestRate(BigDecimal.valueOf(rowi.getCell(6).getNumericCellValue()));
				} catch (Exception e) {
					product.setPredictInterestRate(BigDecimal.valueOf(0D));
				}
				// 宣告利率
				try {
					product.setDeclareInterestRate(BigDecimal.valueOf(rowi.getCell(7).getNumericCellValue()));
				} catch (Exception e) {
					product.setDeclareInterestRate(BigDecimal.valueOf(0D));
				}
				// 利率別
				if (!product.getDeclareInterestRate().equals(BigDecimal.valueOf(0D))) {
					product.setInterestRateType("宣告利率");
				} else if (!product.getPredictInterestRate().equals(BigDecimal.valueOf(0D))) {
					product.setInterestRateType("預定利率");
				}

				// 繳費方式
				product.setPaymentMethod(rowi.getCell(8).getStringCellValue());

				// 點數趴數
				product.setBonusPoint(BigDecimal.valueOf(rowi.getCell(9).getNumericCellValue()));

				// 寫進資料庫
				ProductEntity productEntity = dao.save(product);

				// 切換換第二頁 (基本保費)
				org.apache.poi.ss.usermodel.Sheet sheet1 = wb.getSheetAt(1);
				// 第三排開始往下讀
				for (int j = 2; j <= sheet1.getLastRowNum(); j++) {
					Row rowj = sheet1.getRow(j);

					if (rowj.getCell(0).getStringCellValue().equals(productEntity.getInsurer().getShortName())
							&& rowj.getCell(1).getStringCellValue().equals(productEntity.getLocalName())
							&& (int) (rowj.getCell(2).getNumericCellValue()) == productEntity.getYear()
							&& rowj.getCell(3).getStringCellValue().equals(productEntity.getInterestRateType())
							&& (int) (rowj.getCell(4).getNumericCellValue()) == Integer
									.parseInt(productEntity.getYearCode())
							&& rowj.getCell(5).getStringCellValue().equals(productEntity.getCode())
							&& rowj.getCell(6).getStringCellValue().equals(rowi.getCell(5).getStringCellValue())) {
						ProductPremiumRatio productPremiumRatio = new ProductPremiumRatio();
						// 設定商品ID
						productPremiumRatio.setProductId(productEntity.getId());
						// 設定性別
						productPremiumRatio.setGender(rowj.getCell(7).getStringCellValue());
						// 設定投保年齡
						productPremiumRatio.setInsAge((int) (rowj.getCell(8).getNumericCellValue()));
						// 設定費率
						productPremiumRatio.setPremiumRatio(BigDecimal.valueOf(rowj.getCell(9).getNumericCellValue()));

						// 寫進基本費率 table
						productPremiumRatioDao.save(productPremiumRatio);
						productPremiumRatioList.add(productPremiumRatio);
					} else {
						continue;
					}
				}

				productEntity.setPremiumRatios(productPremiumRatioList);
				// ----------------------------------------------------------------------------------------------------
				// 切換至第三頁 (違約金費率)
				org.apache.poi.ss.usermodel.Sheet sheet2 = wb.getSheetAt(2);

				for (int k = 2; k <= sheet2.getLastRowNum(); k++) {
					Row rowk = sheet2.getRow(k);
					if (rowk.getCell(0).getStringCellValue().equals(productEntity.getInsurer().getShortName())
							&& rowk.getCell(1).getStringCellValue().equals(productEntity.getLocalName())
							&& (int) (rowk.getCell(2).getNumericCellValue()) == productEntity.getYear()
							&& rowk.getCell(3).getStringCellValue().equals(productEntity.getInterestRateType())
							&& (int) (rowk.getCell(4).getNumericCellValue()) == Integer
									.parseInt(productEntity.getYearCode())
							&& rowk.getCell(5).getStringCellValue().equals(productEntity.getCode())
							&& rowk.getCell(6).getStringCellValue().equals(rowi.getCell(5).getStringCellValue())) {

						ProductCancelRatio productCancelRatio = new ProductCancelRatio();
						// 設定商品ID
						productCancelRatio.setProductId(productEntity.getId());
						// 違約金投保年齡
						productCancelRatio.setInsAge((int) rowk.getCell(8).getNumericCellValue());
						// 違約金性別
						productCancelRatio.setGender(rowk.getCell(7).getStringCellValue());
						// 第0年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_0(BigDecimal.valueOf(rowk.getCell(9).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_0(BigDecimal.valueOf(0D));
						}
						// 第1年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_1(BigDecimal.valueOf(rowk.getCell(10).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_1(BigDecimal.valueOf(0D));
						}
						// 第2年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_2(BigDecimal.valueOf(rowk.getCell(11).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_2(BigDecimal.valueOf(0D));
						}
						// 第3年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_3(BigDecimal.valueOf(rowk.getCell(12).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_3(BigDecimal.valueOf(0D));
						}
						// 第4年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_4(BigDecimal.valueOf(rowk.getCell(13).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_4(BigDecimal.valueOf(0D));
						}
						// 第5年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_5(BigDecimal.valueOf(rowk.getCell(14).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_5(BigDecimal.valueOf(0D));
						}
						// 第6年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_6(BigDecimal.valueOf(rowk.getCell(15).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_6(BigDecimal.valueOf(0D));
						}
						// 第7年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_7(BigDecimal.valueOf(rowk.getCell(16).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_7(BigDecimal.valueOf(0D));
						}
						// 第8年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_8(BigDecimal.valueOf(rowk.getCell(17).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_8(BigDecimal.valueOf(0D));
						}
						// 第9年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_9(BigDecimal.valueOf(rowk.getCell(18).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_9(BigDecimal.valueOf(0D));
						}
						// 第10年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_10(BigDecimal.valueOf(rowk.getCell(19).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_10(BigDecimal.valueOf(0D));
						}
						// 第11年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_11(BigDecimal.valueOf(rowk.getCell(20).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_11(BigDecimal.valueOf(0D));
						}
						// 第12年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_12(BigDecimal.valueOf(rowk.getCell(21).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_12(BigDecimal.valueOf(0D));
						}
						// 第13年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_13(BigDecimal.valueOf(rowk.getCell(22).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_13(BigDecimal.valueOf(0D));
						}
						// 第14年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_14(BigDecimal.valueOf(rowk.getCell(23).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_14(BigDecimal.valueOf(0D));
						}
						// 第15年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_15(BigDecimal.valueOf(rowk.getCell(24).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_15(BigDecimal.valueOf(0D));
						}
						// 第16年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_16(BigDecimal.valueOf(rowk.getCell(25).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_16(BigDecimal.valueOf(0D));
						}
						// 第17年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_17(BigDecimal.valueOf(rowk.getCell(26).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_17(BigDecimal.valueOf(0D));
						}
						// 第18年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_18(BigDecimal.valueOf(rowk.getCell(27).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_18(BigDecimal.valueOf(0D));
						}
						// 第19年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_19(BigDecimal.valueOf(rowk.getCell(28).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_19(BigDecimal.valueOf(0D));
						}
						// 第20年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_20(BigDecimal.valueOf(rowk.getCell(29).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_20(BigDecimal.valueOf(0D));
						}
						// 第21年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_21(BigDecimal.valueOf(rowk.getCell(30).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_21(BigDecimal.valueOf(0D));
						}
						// 第22年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_22(BigDecimal.valueOf(rowk.getCell(31).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_22(BigDecimal.valueOf(0D));
						}
						// 第23年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_23(BigDecimal.valueOf(rowk.getCell(32).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_23(BigDecimal.valueOf(0D));
						}
						// 第24年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_24(BigDecimal.valueOf(rowk.getCell(33).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_24(BigDecimal.valueOf(0D));
						}
						// 第25年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_25(BigDecimal.valueOf(rowk.getCell(34).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_25(BigDecimal.valueOf(0D));
						}
						// 第26年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_26(BigDecimal.valueOf(rowk.getCell(35).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_26(BigDecimal.valueOf(0D));
						}
						// 第27年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_27(BigDecimal.valueOf(rowk.getCell(36).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_27(BigDecimal.valueOf(0D));
						}
						// 第28年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_28(BigDecimal.valueOf(rowk.getCell(37).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_28(BigDecimal.valueOf(0D));
						}
						// 第29年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_29(BigDecimal.valueOf(rowk.getCell(38).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_29(BigDecimal.valueOf(0D));
						}
						// 第30年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_30(BigDecimal.valueOf(rowk.getCell(39).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_30(BigDecimal.valueOf(0D));
						}
						// 第31年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_31(BigDecimal.valueOf(rowk.getCell(40).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_31(BigDecimal.valueOf(0D));
						}
						// 第32年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_32(BigDecimal.valueOf(rowk.getCell(41).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_32(BigDecimal.valueOf(0D));
						}
						// 第33年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_33(BigDecimal.valueOf(rowk.getCell(42).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_33(BigDecimal.valueOf(0D));
						}
						// 第34年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_34(BigDecimal.valueOf(rowk.getCell(43).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_34(BigDecimal.valueOf(0D));
						}
						// 第35年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_35(BigDecimal.valueOf(rowk.getCell(44).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_35(BigDecimal.valueOf(0D));
						}
						// 第36年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_36(BigDecimal.valueOf(rowk.getCell(45).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_36(BigDecimal.valueOf(0D));
						}
						// 第37年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_37(BigDecimal.valueOf(rowk.getCell(46).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_37(BigDecimal.valueOf(0D));
						}
						// 第38年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_38(BigDecimal.valueOf(rowk.getCell(47).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_38(BigDecimal.valueOf(0D));
						}
						// 第39年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_39(BigDecimal.valueOf(rowk.getCell(48).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_39(BigDecimal.valueOf(0D));
						}
						// 第40年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_40(BigDecimal.valueOf(rowk.getCell(49).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_40(BigDecimal.valueOf(0D));
						}
						// 第41年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_41(BigDecimal.valueOf(rowk.getCell(50).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_41(BigDecimal.valueOf(0D));
						}
						// 第42年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_42(BigDecimal.valueOf(rowk.getCell(51).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_42(BigDecimal.valueOf(0D));
						}
						// 第43年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_43(BigDecimal.valueOf(rowk.getCell(52).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_43(BigDecimal.valueOf(0D));
						}
						// 第44年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_44(BigDecimal.valueOf(rowk.getCell(53).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_44(BigDecimal.valueOf(0D));
						}
						// 第45年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_45(BigDecimal.valueOf(rowk.getCell(54).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_45(BigDecimal.valueOf(0D));
						}
						// 第46年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_46(BigDecimal.valueOf(rowk.getCell(55).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_46(BigDecimal.valueOf(0D));
						}
						// 第47年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_47(BigDecimal.valueOf(rowk.getCell(56).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_47(BigDecimal.valueOf(0D));
						}
						// 第48年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_48(BigDecimal.valueOf(rowk.getCell(57).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_48(BigDecimal.valueOf(0D));
						}
						// 第49年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_49(BigDecimal.valueOf(rowk.getCell(58).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_49(BigDecimal.valueOf(0D));
						}
						// 第50年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_50(BigDecimal.valueOf(rowk.getCell(59).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_50(BigDecimal.valueOf(0D));
						}
						// 第51年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_51(BigDecimal.valueOf(rowk.getCell(60).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_51(BigDecimal.valueOf(0D));
						}
						// 第52年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_52(BigDecimal.valueOf(rowk.getCell(61).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_52(BigDecimal.valueOf(0D));
						}
						// 第53年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_53(BigDecimal.valueOf(rowk.getCell(62).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_53(BigDecimal.valueOf(0D));
						}
						// 第54年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_54(BigDecimal.valueOf(rowk.getCell(63).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_54(BigDecimal.valueOf(0D));
						}
						// 第55年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_55(BigDecimal.valueOf(rowk.getCell(64).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_55(BigDecimal.valueOf(0D));
						}
						// 第56年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_56(BigDecimal.valueOf(rowk.getCell(65).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_56(BigDecimal.valueOf(0D));
						}
						// 第57年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_57(BigDecimal.valueOf(rowk.getCell(66).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_57(BigDecimal.valueOf(0D));
						}
						// 第58年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_58(BigDecimal.valueOf(rowk.getCell(67).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_58(BigDecimal.valueOf(0D));
						}
						// 第59年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_59(BigDecimal.valueOf(rowk.getCell(68).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_59(BigDecimal.valueOf(0D));
						}
						// 第60年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_60(BigDecimal.valueOf(rowk.getCell(69).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_60(BigDecimal.valueOf(0D));
						}
						// 第61年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_61(BigDecimal.valueOf(rowk.getCell(70).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_61(BigDecimal.valueOf(0D));
						}
						// 第62年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_62(BigDecimal.valueOf(rowk.getCell(71).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_62(BigDecimal.valueOf(0D));
						}
						// 第63年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_63(BigDecimal.valueOf(rowk.getCell(72).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_63(BigDecimal.valueOf(0D));
						}
						// 第64年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_64(BigDecimal.valueOf(rowk.getCell(73).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_64(BigDecimal.valueOf(0D));
						}
						// 第65年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_65(BigDecimal.valueOf(rowk.getCell(74).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_65(BigDecimal.valueOf(0D));
						}
						// 第66年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_66(BigDecimal.valueOf(rowk.getCell(75).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_66(BigDecimal.valueOf(0D));
						}
						// 第67年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_67(BigDecimal.valueOf(rowk.getCell(76).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_67(BigDecimal.valueOf(0D));
						}
						// 第68年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_68(BigDecimal.valueOf(rowk.getCell(77).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_68(BigDecimal.valueOf(0D));
						}
						// 第69年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_69(BigDecimal.valueOf(rowk.getCell(78).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_69(BigDecimal.valueOf(0D));
						}
						// 第70年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_70(BigDecimal.valueOf(rowk.getCell(79).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_70(BigDecimal.valueOf(0D));
						}
						// 第71年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_71(BigDecimal.valueOf(rowk.getCell(80).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_71(BigDecimal.valueOf(0D));
						}
						// 第72年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_72(BigDecimal.valueOf(rowk.getCell(81).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_72(BigDecimal.valueOf(0D));
						}
						// 第73年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_73(BigDecimal.valueOf(rowk.getCell(82).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_73(BigDecimal.valueOf(0D));
						}
						// 第74年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_74(BigDecimal.valueOf(rowk.getCell(83).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_74(BigDecimal.valueOf(0D));
						}
						// 第75年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_75(BigDecimal.valueOf(rowk.getCell(84).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_75(BigDecimal.valueOf(0D));
						}
						// 第76年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_76(BigDecimal.valueOf(rowk.getCell(85).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_76(BigDecimal.valueOf(0D));
						}
						// 第77年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_77(BigDecimal.valueOf(rowk.getCell(86).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_77(BigDecimal.valueOf(0D));
						}
						// 第78年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_78(BigDecimal.valueOf(rowk.getCell(87).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_78(BigDecimal.valueOf(0D));
						}
						// 第79年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_79(BigDecimal.valueOf(rowk.getCell(88).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_79(BigDecimal.valueOf(0D));
						}
						// 第80年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_80(BigDecimal.valueOf(rowk.getCell(89).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_80(BigDecimal.valueOf(0D));
						}
						// 第81年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_81(BigDecimal.valueOf(rowk.getCell(90).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_81(BigDecimal.valueOf(0D));
						}
						// 第82年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_82(BigDecimal.valueOf(rowk.getCell(91).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_82(BigDecimal.valueOf(0D));
						}
						// 第83年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_83(BigDecimal.valueOf(rowk.getCell(92).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_83(BigDecimal.valueOf(0D));
						}
						// 第84年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_84(BigDecimal.valueOf(rowk.getCell(93).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_84(BigDecimal.valueOf(0D));
						}
						// 第85年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_85(BigDecimal.valueOf(rowk.getCell(94).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_85(BigDecimal.valueOf(0D));
						}
						// 第86年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_86(BigDecimal.valueOf(rowk.getCell(95).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_86(BigDecimal.valueOf(0D));
						}
						// 第87年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_87(BigDecimal.valueOf(rowk.getCell(96).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_87(BigDecimal.valueOf(0D));
						}
						// 第88年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_88(BigDecimal.valueOf(rowk.getCell(97).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_88(BigDecimal.valueOf(0D));
						}
						// 第89年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_89(BigDecimal.valueOf(rowk.getCell(98).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_89(BigDecimal.valueOf(0D));
						}
						// 第90年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_90(BigDecimal.valueOf(rowk.getCell(99).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_90(BigDecimal.valueOf(0D));
						}
						// 第91年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_91(BigDecimal.valueOf(rowk.getCell(100).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_91(BigDecimal.valueOf(0D));
						}
						// 第92年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_92(BigDecimal.valueOf(rowk.getCell(101).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_92(BigDecimal.valueOf(0D));
						}
						// 第93年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_93(BigDecimal.valueOf(rowk.getCell(102).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_93(BigDecimal.valueOf(0D));
						}
						// 第94年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_94(BigDecimal.valueOf(rowk.getCell(103).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_94(BigDecimal.valueOf(0D));
						}
						// 第95年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_95(BigDecimal.valueOf(rowk.getCell(104).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_95(BigDecimal.valueOf(0D));
						}
						// 第96年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_96(BigDecimal.valueOf(rowk.getCell(105).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_96(BigDecimal.valueOf(0D));
						}
						// 第97年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_97(BigDecimal.valueOf(rowk.getCell(106).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_97(BigDecimal.valueOf(0D));
						}
						// 第98年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_98(BigDecimal.valueOf(rowk.getCell(107).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_98(BigDecimal.valueOf(0D));
						}
						// 第99年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_99(BigDecimal.valueOf(rowk.getCell(108).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_99(BigDecimal.valueOf(0D));
						}
						// 第100年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_100(BigDecimal.valueOf(rowk.getCell(109).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_100(BigDecimal.valueOf(0D));
						}
						// 第101年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_101(BigDecimal.valueOf(rowk.getCell(110).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_101(BigDecimal.valueOf(0D));
						}
						// 第102年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_102(BigDecimal.valueOf(rowk.getCell(111).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_102(BigDecimal.valueOf(0D));
						}
						// 第103年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_103(BigDecimal.valueOf(rowk.getCell(112).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_103(BigDecimal.valueOf(0D));
						}
						// 第104年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_104(BigDecimal.valueOf(rowk.getCell(113).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_104(BigDecimal.valueOf(0D));
						}
						// 第105年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_105(BigDecimal.valueOf(rowk.getCell(114).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_105(BigDecimal.valueOf(0D));
						}
						// 第106年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_106(BigDecimal.valueOf(rowk.getCell(115).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_106(BigDecimal.valueOf(0D));
						}
						// 第107年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_107(BigDecimal.valueOf(rowk.getCell(116).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_107(BigDecimal.valueOf(0D));
						}
						// 第108年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_108(BigDecimal.valueOf(rowk.getCell(117).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_108(BigDecimal.valueOf(0D));
						}
						// 第109年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_109(BigDecimal.valueOf(rowk.getCell(118).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_109(BigDecimal.valueOf(0D));
						}
						// 第110年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_110(BigDecimal.valueOf(rowk.getCell(119).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_110(BigDecimal.valueOf(0D));
						}
						// 第111年違約金費率
						try {
							productCancelRatio
									.setCancelRatio_111(BigDecimal.valueOf(rowk.getCell(120).getNumericCellValue()));
						} catch (Exception e) {
							productCancelRatio.setCancelRatio_111(BigDecimal.valueOf(0D));
						}
						// 寫進違約金table
						productCancelRatioDao.save(productCancelRatio);

						productCancelRatioList.add(productCancelRatio);

					} else {
						continue;
					}

				}
				productEntity.setCancelRatios(productCancelRatioList);
				// -------------------------------------------------------------------------------------------------
				// 切換至第四頁 (高保費率)
				org.apache.poi.ss.usermodel.Sheet sheet3 = wb.getSheetAt(3);

				for (int m = 2; m <= sheet3.getLastRowNum(); m++) {
					Row rowm = sheet3.getRow(m);
					if (rowm.getCell(0).getStringCellValue().equals(productEntity.getInsurer().getShortName())
							&& rowm.getCell(1).getStringCellValue().equals(productEntity.getLocalName())
							&& (int) (rowm.getCell(2).getNumericCellValue()) == productEntity.getYear()
							&& rowm.getCell(3).getStringCellValue().equals(productEntity.getInterestRateType())
							&& (int) (rowm.getCell(4).getNumericCellValue()) == Integer
									.parseInt(productEntity.getYearCode())
							&& rowm.getCell(5).getStringCellValue().equals(productEntity.getCode())
							&& rowm.getCell(6).getStringCellValue().equals(rowi.getCell(5).getStringCellValue())) {
						ProductHighDiscountRatio productHighDiscountRatio = new ProductHighDiscountRatio();
						// 高保費率商品ID
						productHighDiscountRatio.setProductId(productEntity.getId());
						// 折扣趴數
						productHighDiscountRatio
								.setDiscountRatio(BigDecimal.valueOf(rowm.getCell(7).getNumericCellValue()));
						// 下限
						productHighDiscountRatio.setMinValue(BigDecimal.valueOf(rowm.getCell(8).getNumericCellValue()));
						// 上限
						productHighDiscountRatio.setMaxValue((int) rowm.getCell(9).getNumericCellValue());

						// 寫入高保費table
						productHighDiscountRatioDao.save(productHighDiscountRatio);

						productHighDiscountRatioList.add(productHighDiscountRatio);
					} else {
						continue;
					}
				}
				productEntity.setHighDiscountRatios(productHighDiscountRatioList);

				// 更新product table
				productEntity.setCreatedTime(new Timestamp(new Date().getTime()));
				dao.save(productEntity);
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}