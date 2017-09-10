package tw.com.triplei.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private String url;

	@Override
	public GenericDao<ProductEntity> getDao() {
		return dao;
	}

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

	public boolean ProductUpload(MultipartFile file) {
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String path = "src/main/resources/files";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();

			String date = DateTimeFormatter.ofPattern("MM-dd_HHmmss").format(LocalDateTime.now());
			File serverFile = new File(dir.getAbsolutePath() + File.separator + date + file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			url = path+"/"+date+file.getOriginalFilename();
			stream.write(bytes);
			stream.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	public boolean insertXlsxToDB() throws Exception{
		org.apache.poi.ss.usermodel.Sheet sheet;
		org.apache.poi.ss.usermodel.Workbook wb = null;
		try {
			if(url.endsWith(".xlsx")){
				wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook(new File(url));
			}else if(url.endsWith(".xls")){
				POIFSFileSystem fs = new POIFSFileSystem(new File(url));
				wb = new org.apache.poi.hssf.usermodel.HSSFWorkbook(fs);
			};

			
			sheet = wb.getSheetAt(0);//取得第一頁的資料

			//從第3排開始往下讀
			for(int i=2;i<=sheet.getLastRowNum();i++){
				Row rowi = sheet.getRow(i);
				if(rowi.getCell(0).getStringCellValue()==""){
					break;
				}
				
				Collection<ProductPremiumRatio> productPremiumRatioList = new ArrayList<ProductPremiumRatio>();
				Collection<ProductHighDiscountRatio> productHighDiscountRatioList = new ArrayList<ProductHighDiscountRatio>();
				Collection<ProductCancelRatio> productCancelRatioList = new ArrayList<ProductCancelRatio>();		
				
				ProductEntity product = new ProductEntity();

				//保險公司
				String insurerName = rowi.getCell(0).getStringCellValue();
				InsurerEntity insurer = insurerDao.findByShortName(insurerName);
				product.setInsurer(insurer);
				//商品名稱
				product.setLocalName(rowi.getCell(1).getStringCellValue());
				//年期
				product.setYear((int)rowi.getCell(2).getNumericCellValue());
				//商品年度
				product.setYearCode((int)rowi.getCell(3).getNumericCellValue()+"");
				//商品代碼
				product.setCode(rowi.getCell(4).getStringCellValue());
				//幣別
				if("美元".equals(rowi.getCell(5).getStringCellValue())){
					product.setCurr(Currency.USD);
				}else if("新台幣".equals(rowi.getCell(5).getStringCellValue())){
					product.setCurr(Currency.TWD);
				};		
				//預定利率
				product.setPredictInterestRate(BigDecimal.valueOf(rowi.getCell(6).getNumericCellValue()));

				//宣告利率
				product.setPredictInterestRate(BigDecimal.valueOf(rowi.getCell(7).getNumericCellValue()));

				//繳費方式
				product.setPaymentMethod(rowi.getCell(8).getStringCellValue());	

				//點數趴數
				product.setBonusPoint(BigDecimal.valueOf(rowi.getCell(9).getNumericCellValue()));
				
				//寫進資料庫
				ProductEntity productEntity = dao.save(product);
				
				//切換換第二頁 (基本保費)
				org.apache.poi.ss.usermodel.Sheet sheet1 = wb.getSheetAt(1);
				//第三排開始往下讀
				for(int j=2;j<=sheet1.getLastRowNum();j++){
					Row rowj = sheet1.getRow(j);					
					
					if(rowj.getCell(0).getStringCellValue().equals(productEntity.getInsurer().getShortName()) &&
						rowj.getCell(1).getStringCellValue().equals(productEntity.getLocalName()) &&
						(int)(rowj.getCell(2).getNumericCellValue()) == productEntity.getYear() &&
						(int)(rowj.getCell(4).getNumericCellValue()) == Integer.parseInt(productEntity.getYearCode()) &&
						rowj.getCell(5).getStringCellValue().equals(productEntity.getCode()) &&
						rowj.getCell(6).getStringCellValue().equals(rowi.getCell(5).getStringCellValue())
							){
						ProductPremiumRatio productPremiumRatio = new ProductPremiumRatio();
						//設定商品ID
						productPremiumRatio.setProductId(productEntity.getId());
						//設定性別
						productPremiumRatio.setGender(rowj.getCell(7).getStringCellValue());
						//設定投保年齡
						productPremiumRatio.setInsAge((int)(rowj.getCell(8).getNumericCellValue()));
						//設定費率
						productPremiumRatio.setPremiumRatio(BigDecimal.valueOf(rowj.getCell(9).getNumericCellValue()));

						//寫進基本費率 table
						productPremiumRatioDao.save(productPremiumRatio);
						productPremiumRatioList.add(productPremiumRatio);
					}else{
						break;
					}
				}

				productEntity.setPremiumRatios(productPremiumRatioList);
//----------------------------------------------------------------------------------------------------				
				//切換至第三頁 (違約金費率)
				org.apache.poi.ss.usermodel.Sheet sheet2 = wb.getSheetAt(2);
				for(int k=2;k<=sheet2.getLastRowNum();k++){
					Row rowk = sheet2.getRow(k);
					if(rowk.getCell(0).getStringCellValue().equals(productEntity.getInsurer().getShortName()) &&
						rowk.getCell(1).getStringCellValue().equals(productEntity.getLocalName()) &&
						(int)(rowk.getCell(2).getNumericCellValue()) == productEntity.getYear() &&
						(int)(rowk.getCell(4).getNumericCellValue()) == Integer.parseInt(productEntity.getYearCode()) &&
						rowk.getCell(5).getStringCellValue().equals(productEntity.getCode()) &&
						rowk.getCell(6).getStringCellValue().equals(rowi.getCell(5).getStringCellValue())
							){
						ProductCancelRatio productCancelRatio = new ProductCancelRatio();
						
						//設定商品ID
						productCancelRatio.setProductId(productEntity.getId());
						//違約金投保年齡
						productCancelRatio.setInsAge((int)rowk.getCell(8).getNumericCellValue());
						//違約金性別
						productCancelRatio.setGender(rowk.getCell(7).getStringCellValue());
						//第0年違約金費率
						productCancelRatio.setCancelRatio_0(BigDecimal.valueOf(rowk.getCell(9).getNumericCellValue()));
						//第1年違約金費率
						productCancelRatio.setCancelRatio_1(BigDecimal.valueOf(rowk.getCell(10).getNumericCellValue()));
						//第2年違約金費率
						productCancelRatio.setCancelRatio_2(BigDecimal.valueOf(rowk.getCell(11).getNumericCellValue()));
						//第3年違約金費率
						productCancelRatio.setCancelRatio_3(BigDecimal.valueOf(rowk.getCell(12).getNumericCellValue()));
						//第4年違約金費率
						productCancelRatio.setCancelRatio_4(BigDecimal.valueOf(rowk.getCell(13).getNumericCellValue()));
						//第5年違約金費率
						productCancelRatio.setCancelRatio_5(BigDecimal.valueOf(rowk.getCell(14).getNumericCellValue()));
						//第6年違約金費率
						productCancelRatio.setCancelRatio_6(BigDecimal.valueOf(rowk.getCell(15).getNumericCellValue()));
						//第7年違約金費率
						productCancelRatio.setCancelRatio_7(BigDecimal.valueOf(rowk.getCell(16).getNumericCellValue()));
						//第8年違約金費率
						productCancelRatio.setCancelRatio_8(BigDecimal.valueOf(rowk.getCell(17).getNumericCellValue()));
						//第9年違約金費率
						productCancelRatio.setCancelRatio_9(BigDecimal.valueOf(rowk.getCell(18).getNumericCellValue()));
						//第10年違約金費率
						productCancelRatio.setCancelRatio_10(BigDecimal.valueOf(rowk.getCell(19).getNumericCellValue()));
						//第11年違約金費率
						productCancelRatio.setCancelRatio_11(BigDecimal.valueOf(rowk.getCell(20).getNumericCellValue()));
						//第12年違約金費率
						productCancelRatio.setCancelRatio_12(BigDecimal.valueOf(rowk.getCell(21).getNumericCellValue()));
						//第13年違約金費率
						productCancelRatio.setCancelRatio_13(BigDecimal.valueOf(rowk.getCell(22).getNumericCellValue()));
						//第14年違約金費率
						productCancelRatio.setCancelRatio_14(BigDecimal.valueOf(rowk.getCell(23).getNumericCellValue()));
						//第15年違約金費率
						productCancelRatio.setCancelRatio_15(BigDecimal.valueOf(rowk.getCell(24).getNumericCellValue()));
						//第16年違約金費率
						productCancelRatio.setCancelRatio_16(BigDecimal.valueOf(rowk.getCell(25).getNumericCellValue()));
						//第17年違約金費率
						productCancelRatio.setCancelRatio_17(BigDecimal.valueOf(rowk.getCell(26).getNumericCellValue()));
						//第18年違約金費率
						productCancelRatio.setCancelRatio_18(BigDecimal.valueOf(rowk.getCell(27).getNumericCellValue()));
						//第19年違約金費率
						productCancelRatio.setCancelRatio_19(BigDecimal.valueOf(rowk.getCell(28).getNumericCellValue()));
						//第20年違約金費率
						productCancelRatio.setCancelRatio_20(BigDecimal.valueOf(rowk.getCell(29).getNumericCellValue()));
						//第21年違約金費率
						productCancelRatio.setCancelRatio_21(BigDecimal.valueOf(rowk.getCell(30).getNumericCellValue()));
						//第22年違約金費率
						productCancelRatio.setCancelRatio_22(BigDecimal.valueOf(rowk.getCell(31).getNumericCellValue()));
						//第23年違約金費率
						productCancelRatio.setCancelRatio_23(BigDecimal.valueOf(rowk.getCell(32).getNumericCellValue()));
						//第24年違約金費率
						productCancelRatio.setCancelRatio_24(BigDecimal.valueOf(rowk.getCell(33).getNumericCellValue()));
						//第25年違約金費率
						productCancelRatio.setCancelRatio_25(BigDecimal.valueOf(rowk.getCell(34).getNumericCellValue()));
						//第26年違約金費率
						productCancelRatio.setCancelRatio_26(BigDecimal.valueOf(rowk.getCell(35).getNumericCellValue()));
						//第27年違約金費率
						productCancelRatio.setCancelRatio_27(BigDecimal.valueOf(rowk.getCell(36).getNumericCellValue()));
						//第28年違約金費率
						productCancelRatio.setCancelRatio_28(BigDecimal.valueOf(rowk.getCell(37).getNumericCellValue()));
						//第29年違約金費率
						productCancelRatio.setCancelRatio_29(BigDecimal.valueOf(rowk.getCell(38).getNumericCellValue()));
						//第30年違約金費率
						productCancelRatio.setCancelRatio_30(BigDecimal.valueOf(rowk.getCell(39).getNumericCellValue()));
						//第31年違約金費率
						productCancelRatio.setCancelRatio_31(BigDecimal.valueOf(rowk.getCell(40).getNumericCellValue()));
						//第32年違約金費率
						productCancelRatio.setCancelRatio_32(BigDecimal.valueOf(rowk.getCell(41).getNumericCellValue()));
						//第33年違約金費率
						productCancelRatio.setCancelRatio_33(BigDecimal.valueOf(rowk.getCell(42).getNumericCellValue()));
						//第34年違約金費率
						productCancelRatio.setCancelRatio_34(BigDecimal.valueOf(rowk.getCell(43).getNumericCellValue()));
						//第35年違約金費率
						productCancelRatio.setCancelRatio_35(BigDecimal.valueOf(rowk.getCell(44).getNumericCellValue()));
						//第36年違約金費率
						productCancelRatio.setCancelRatio_36(BigDecimal.valueOf(rowk.getCell(45).getNumericCellValue()));
						//第37年違約金費率
						productCancelRatio.setCancelRatio_37(BigDecimal.valueOf(rowk.getCell(46).getNumericCellValue()));
						//第38年違約金費率
						productCancelRatio.setCancelRatio_38(BigDecimal.valueOf(rowk.getCell(47).getNumericCellValue()));
						//第39年違約金費率
						productCancelRatio.setCancelRatio_39(BigDecimal.valueOf(rowk.getCell(48).getNumericCellValue()));
						//第40年違約金費率
						productCancelRatio.setCancelRatio_40(BigDecimal.valueOf(rowk.getCell(49).getNumericCellValue()));
						//第41年違約金費率
						productCancelRatio.setCancelRatio_41(BigDecimal.valueOf(rowk.getCell(50).getNumericCellValue()));
						//第42年違約金費率
						productCancelRatio.setCancelRatio_42(BigDecimal.valueOf(rowk.getCell(51).getNumericCellValue()));
						//第43年違約金費率
						productCancelRatio.setCancelRatio_43(BigDecimal.valueOf(rowk.getCell(52).getNumericCellValue()));
						//第44年違約金費率
						productCancelRatio.setCancelRatio_44(BigDecimal.valueOf(rowk.getCell(53).getNumericCellValue()));
						//第45年違約金費率
						productCancelRatio.setCancelRatio_45(BigDecimal.valueOf(rowk.getCell(54).getNumericCellValue()));
						//第46年違約金費率
						productCancelRatio.setCancelRatio_46(BigDecimal.valueOf(rowk.getCell(55).getNumericCellValue()));
						//第47年違約金費率
						productCancelRatio.setCancelRatio_47(BigDecimal.valueOf(rowk.getCell(56).getNumericCellValue()));
						//第48年違約金費率
						productCancelRatio.setCancelRatio_48(BigDecimal.valueOf(rowk.getCell(57).getNumericCellValue()));
						//第49年違約金費率
						productCancelRatio.setCancelRatio_49(BigDecimal.valueOf(rowk.getCell(58).getNumericCellValue()));
						//第50年違約金費率
						productCancelRatio.setCancelRatio_50(BigDecimal.valueOf(rowk.getCell(59).getNumericCellValue()));
						//第51年違約金費率
						productCancelRatio.setCancelRatio_51(BigDecimal.valueOf(rowk.getCell(60).getNumericCellValue()));
						//第52年違約金費率
						productCancelRatio.setCancelRatio_52(BigDecimal.valueOf(rowk.getCell(61).getNumericCellValue()));
						//第53年違約金費率
						productCancelRatio.setCancelRatio_53(BigDecimal.valueOf(rowk.getCell(62).getNumericCellValue()));
						//第54年違約金費率
						productCancelRatio.setCancelRatio_54(BigDecimal.valueOf(rowk.getCell(63).getNumericCellValue()));
						//第55年違約金費率
						productCancelRatio.setCancelRatio_55(BigDecimal.valueOf(rowk.getCell(64).getNumericCellValue()));
						//第56年違約金費率
						productCancelRatio.setCancelRatio_56(BigDecimal.valueOf(rowk.getCell(65).getNumericCellValue()));
						//第57年違約金費率
						productCancelRatio.setCancelRatio_57(BigDecimal.valueOf(rowk.getCell(66).getNumericCellValue()));
						//第58年違約金費率
						productCancelRatio.setCancelRatio_58(BigDecimal.valueOf(rowk.getCell(67).getNumericCellValue()));
						//第59年違約金費率
						productCancelRatio.setCancelRatio_59(BigDecimal.valueOf(rowk.getCell(68).getNumericCellValue()));
						//第60年違約金費率
						productCancelRatio.setCancelRatio_60(BigDecimal.valueOf(rowk.getCell(69).getNumericCellValue()));
						//第61年違約金費率
						productCancelRatio.setCancelRatio_61(BigDecimal.valueOf(rowk.getCell(70).getNumericCellValue()));
						//第62年違約金費率
						productCancelRatio.setCancelRatio_62(BigDecimal.valueOf(rowk.getCell(71).getNumericCellValue()));
						//第63年違約金費率
						productCancelRatio.setCancelRatio_63(BigDecimal.valueOf(rowk.getCell(72).getNumericCellValue()));
						//第64年違約金費率
						productCancelRatio.setCancelRatio_64(BigDecimal.valueOf(rowk.getCell(73).getNumericCellValue()));
						//第65年違約金費率
						productCancelRatio.setCancelRatio_65(BigDecimal.valueOf(rowk.getCell(74).getNumericCellValue()));
						//第66年違約金費率
						productCancelRatio.setCancelRatio_66(BigDecimal.valueOf(rowk.getCell(75).getNumericCellValue()));
						//第67年違約金費率
						productCancelRatio.setCancelRatio_67(BigDecimal.valueOf(rowk.getCell(76).getNumericCellValue()));
						//第68年違約金費率
						productCancelRatio.setCancelRatio_68(BigDecimal.valueOf(rowk.getCell(77).getNumericCellValue()));
						//第69年違約金費率
						productCancelRatio.setCancelRatio_69(BigDecimal.valueOf(rowk.getCell(78).getNumericCellValue()));
						//第70年違約金費率
						productCancelRatio.setCancelRatio_70(BigDecimal.valueOf(rowk.getCell(79).getNumericCellValue()));
						//第71年違約金費率
						productCancelRatio.setCancelRatio_71(BigDecimal.valueOf(rowk.getCell(80).getNumericCellValue()));
						//第72年違約金費率
						productCancelRatio.setCancelRatio_72(BigDecimal.valueOf(rowk.getCell(81).getNumericCellValue()));
						//第73年違約金費率
						productCancelRatio.setCancelRatio_73(BigDecimal.valueOf(rowk.getCell(82).getNumericCellValue()));
						//第74年違約金費率
						productCancelRatio.setCancelRatio_74(BigDecimal.valueOf(rowk.getCell(83).getNumericCellValue()));
						//第75年違約金費率
						productCancelRatio.setCancelRatio_75(BigDecimal.valueOf(rowk.getCell(84).getNumericCellValue()));
						//第76年違約金費率
						productCancelRatio.setCancelRatio_76(BigDecimal.valueOf(rowk.getCell(85).getNumericCellValue()));
						//第77年違約金費率
						productCancelRatio.setCancelRatio_77(BigDecimal.valueOf(rowk.getCell(86).getNumericCellValue()));
						//第78年違約金費率
						productCancelRatio.setCancelRatio_78(BigDecimal.valueOf(rowk.getCell(87).getNumericCellValue()));
						//第79年違約金費率
						productCancelRatio.setCancelRatio_79(BigDecimal.valueOf(rowk.getCell(88).getNumericCellValue()));
						//第80年違約金費率
						productCancelRatio.setCancelRatio_80(BigDecimal.valueOf(rowk.getCell(89).getNumericCellValue()));
						//第81年違約金費率
						productCancelRatio.setCancelRatio_81(BigDecimal.valueOf(rowk.getCell(90).getNumericCellValue()));
						//第82年違約金費率
						productCancelRatio.setCancelRatio_82(BigDecimal.valueOf(rowk.getCell(91).getNumericCellValue()));
						//第83年違約金費率
						productCancelRatio.setCancelRatio_83(BigDecimal.valueOf(rowk.getCell(92).getNumericCellValue()));
						//第84年違約金費率
						productCancelRatio.setCancelRatio_84(BigDecimal.valueOf(rowk.getCell(93).getNumericCellValue()));
						//第85年違約金費率
						productCancelRatio.setCancelRatio_85(BigDecimal.valueOf(rowk.getCell(94).getNumericCellValue()));
						//第86年違約金費率
						productCancelRatio.setCancelRatio_86(BigDecimal.valueOf(rowk.getCell(95).getNumericCellValue()));
						//第87年違約金費率
						productCancelRatio.setCancelRatio_87(BigDecimal.valueOf(rowk.getCell(96).getNumericCellValue()));
						//第88年違約金費率
						productCancelRatio.setCancelRatio_88(BigDecimal.valueOf(rowk.getCell(97).getNumericCellValue()));
						//第89年違約金費率
						productCancelRatio.setCancelRatio_89(BigDecimal.valueOf(rowk.getCell(98).getNumericCellValue()));
						//第90年違約金費率
						productCancelRatio.setCancelRatio_90(BigDecimal.valueOf(rowk.getCell(99).getNumericCellValue()));
						//第91年違約金費率
						productCancelRatio.setCancelRatio_91(BigDecimal.valueOf(rowk.getCell(100).getNumericCellValue()));
						//第92年違約金費率
						productCancelRatio.setCancelRatio_92(BigDecimal.valueOf(rowk.getCell(101).getNumericCellValue()));
						//第93年違約金費率
						productCancelRatio.setCancelRatio_93(BigDecimal.valueOf(rowk.getCell(102).getNumericCellValue()));
						//第94年違約金費率
						productCancelRatio.setCancelRatio_94(BigDecimal.valueOf(rowk.getCell(103).getNumericCellValue()));
						//第95年違約金費率
						productCancelRatio.setCancelRatio_95(BigDecimal.valueOf(rowk.getCell(104).getNumericCellValue()));
						//第96年違約金費率
						productCancelRatio.setCancelRatio_96(BigDecimal.valueOf(rowk.getCell(105).getNumericCellValue()));
						//第97年違約金費率
						productCancelRatio.setCancelRatio_97(BigDecimal.valueOf(rowk.getCell(106).getNumericCellValue()));
						//第98年違約金費率
						productCancelRatio.setCancelRatio_98(BigDecimal.valueOf(rowk.getCell(107).getNumericCellValue()));
						//第99年違約金費率
						productCancelRatio.setCancelRatio_99(BigDecimal.valueOf(rowk.getCell(108).getNumericCellValue()));
						//第100年違約金費率
						productCancelRatio.setCancelRatio_100(BigDecimal.valueOf(rowk.getCell(109).getNumericCellValue()));
						//第101年違約金費率
						productCancelRatio.setCancelRatio_101(BigDecimal.valueOf(rowk.getCell(110).getNumericCellValue()));
						//第102年違約金費率
						productCancelRatio.setCancelRatio_102(BigDecimal.valueOf(rowk.getCell(111).getNumericCellValue()));
						//第103年違約金費率
						productCancelRatio.setCancelRatio_103(BigDecimal.valueOf(rowk.getCell(112).getNumericCellValue()));
						//第104年違約金費率
						productCancelRatio.setCancelRatio_104(BigDecimal.valueOf(rowk.getCell(113).getNumericCellValue()));
						//第105年違約金費率
						productCancelRatio.setCancelRatio_105(BigDecimal.valueOf(rowk.getCell(114).getNumericCellValue()));
						//第106年違約金費率
						productCancelRatio.setCancelRatio_106(BigDecimal.valueOf(rowk.getCell(115).getNumericCellValue()));
						//第107年違約金費率
						productCancelRatio.setCancelRatio_107(BigDecimal.valueOf(rowk.getCell(116).getNumericCellValue()));
						//第108年違約金費率
						productCancelRatio.setCancelRatio_108(BigDecimal.valueOf(rowk.getCell(117).getNumericCellValue()));
						//第109年違約金費率
						productCancelRatio.setCancelRatio_109(BigDecimal.valueOf(rowk.getCell(118).getNumericCellValue()));
						//第110年違約金費率
						productCancelRatio.setCancelRatio_110(BigDecimal.valueOf(rowk.getCell(119).getNumericCellValue()));
						//第111年違約金費率
						productCancelRatio.setCancelRatio_111(BigDecimal.valueOf(rowk.getCell(120).getNumericCellValue()));			
						
						//寫進違約金table
						productCancelRatioDao.save(productCancelRatio);
						
						productCancelRatioList.add(productCancelRatio);
					
					}			
					
				}
				productEntity.setCancelRatios(productCancelRatioList);
				//更新product table
//				dao.save(productEntity);
//-------------------------------------------------------------------------------------------------
				//切換至第四頁 (高保費率)
				org.apache.poi.ss.usermodel.Sheet sheet3 = wb.getSheetAt(3);
				
				for(int m = 2;m<=sheet3.getLastRowNum();m++){
					Row rowm = sheet3.getRow(m);
					if(rowm.getCell(0).getStringCellValue().equals(productEntity.getInsurer().getShortName()) &&
							rowm.getCell(1).getStringCellValue().equals(productEntity.getLocalName()) &&
							(int)(rowm.getCell(2).getNumericCellValue()) == productEntity.getYear() &&
							(int)(rowm.getCell(4).getNumericCellValue()) == Integer.parseInt(productEntity.getYearCode()) &&
							rowm.getCell(5).getStringCellValue().equals(productEntity.getCode()) &&
							rowm.getCell(6).getStringCellValue().equals(rowi.getCell(5).getStringCellValue())
								){
						ProductHighDiscountRatio productHighDiscountRatio = new ProductHighDiscountRatio();
						//高保費率商品ID
						productHighDiscountRatio.setProductId(productEntity.getId());
						//折扣趴數
						productHighDiscountRatio.setDiscountRatio(BigDecimal.valueOf(rowm.getCell(7).getNumericCellValue()));
						//下限
						productHighDiscountRatio.setMinValue(BigDecimal.valueOf(rowm.getCell(8).getNumericCellValue()));
						//上限
						productHighDiscountRatio.setMaxValue((int)rowm.getCell(9).getNumericCellValue());
						
						
						//寫入高保費table
						productHighDiscountRatioDao.save(productHighDiscountRatio);
						
						productHighDiscountRatioList.add(productHighDiscountRatio);
					}
				}

				productEntity.setHighDiscountRatios(productHighDiscountRatioList);
				dao.save(productEntity);
			}
			
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}