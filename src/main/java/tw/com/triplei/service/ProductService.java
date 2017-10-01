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

import org.apache.commons.beanutils.MethodUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	private Environment env;
	
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

	public boolean deleteAll(long id) {
		if (this.findProduct(id) != null) {
			productPremiumRatioDao.deleteByPorductId(id);
			productCancelRatioDao.deleteByPorductId(id);
			productHighDiscountRatioDao.deleteByPorductId(id);
			this.delete(id);
			return true;
		}
		return false;
	}

	public ProductEntity findProduct(long id) {
		ProductEntity product = dao.findOne(id);
		product.setPremiumRatios(productPremiumRatioDao.findByProductId(id));
		product.setCancelRatios(productCancelRatioDao.findByProductId(id));
		product.setHighDiscountRatios(productHighDiscountRatioDao.findByProductId(id));
		return product;
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

//	public double getPremiumRatio(ProductEntity product) {
//		double premiumRatio = 0;
//		try {
//			log.debug("{}", product);
//			premiumRatio = product.getPremiumRatios().iterator().next().getPremiumRatio().doubleValue();
//		} catch (Exception e) {
//			System.out.println("NOOOO");
//			e.printStackTrace();
//		}
//		return premiumRatio;
//	}

	public List<ProductEntity> search(String gender, int insAge, Currency currency, String interestRateType, int year) {

		List<ProductEntity> products = dao.findByCurrAndInterestRateTypeAndYear(currency, interestRateType, year);
		log.debug("products{}", products);
		List<ProductEntity> productss = new ArrayList<>();
		for (ProductEntity product : products) {
			log.debug("productID{},Age{},Gender{}", product.getId(), insAge, gender);
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

//	public BigDecimal toCancelRatio(int yearCode, ProductEntity product) throws Exception {
//		ProductCancelRatio productCancelRatio = product.getCancelRatios().iterator().next();
//		MethodUtils methodUtils = new MethodUtils();
//		if (methodUtils.invokeMethod(productCancelRatio, "getCancelRatio_" + yearCode, null) != null) {
//			return (BigDecimal) methodUtils.invokeMethod(productCancelRatio, "getCancelRatio_" + yearCode, null);
//		} else {
//			return null;
//		}
//	}

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
			String path = env.getProperty("productUploadPath");
			log.debug("Product Upload Path{}",path);
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
		double iii = 0D;
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
					iii = BigDecimal.valueOf(rowi.getCell(7).getNumericCellValue()).doubleValue();
					product.setDeclareInterestRate(
							BigDecimal.valueOf(rowi.getCell(7).getNumericCellValue()).setScale(3));
				} catch (Exception e) {
					product.setDeclareInterestRate(BigDecimal.valueOf(0D));
				}
				// 利率別
				if (!(product.getDeclareInterestRate().doubleValue() == 0)) {
					product.setInterestRateType("宣告利率");
				} else if (!(product.getPredictInterestRate().doubleValue() == 0)) {
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

						// 0~111年的解約金費率
						for (int x = 0; x <= 111; x++) {
							MethodUtils methodUtils = new MethodUtils();
							try {
								methodUtils.invokeMethod(productCancelRatio, "setCancelRatio_" + x,
										BigDecimal.valueOf(rowk.getCell(x + 9).getNumericCellValue()));
							} catch (Exception e) {
								methodUtils.invokeMethod(productCancelRatio, "setCancelRatio_" + x,
										BigDecimal.valueOf(0D));
							}
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
				UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				log.debug("userDetails: {}",userDetails);
				productEntity.setCreatedBy(userDetails.getUsername());
				dao.save(productEntity);
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ProductEntity getOneAll(long id) {
		ProductEntity product = dao.findOne(id);
		product.setPremiumRatios(productPremiumRatioDao.findByProductId(id));
		product.setHighDiscountRatios(productHighDiscountRatioDao.findByProductId(id));
		product.setCancelRatios(productCancelRatioDao.findByProductId(id));
		return product;
	}

}