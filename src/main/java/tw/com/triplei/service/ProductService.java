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