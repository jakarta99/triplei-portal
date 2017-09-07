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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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
			String xxx = "src/main/resources/files";
			File dir = new File(xxx);
			if (!dir.exists())
				dir.mkdirs();

			String date = DateTimeFormatter.ofPattern("MM-dd_HHmmss").format(LocalDateTime.now());
			File serverFile = new File(dir.getAbsolutePath() + File.separator + date + file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			url = xxx+"/"+date+file.getOriginalFilename();
			System.out.println(url);
			stream.write(bytes);
			stream.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean insertXlsToDB(){
		Sheet sheet;
		Workbook book;
		try {
			book = Workbook.getWorkbook(new File(url));
			System.out.println(url);
			sheet = book.getSheet(0);//取得第一頁的資料
			for(int i=2;i<3;i++){//從第3排開始往下讀
				Collection<ProductPremiumRatio> productPremiumRatioList = new ArrayList<ProductPremiumRatio>();
				Collection<ProductHighDiscountRatio> productHighDiscountRatioList = new ArrayList<ProductHighDiscountRatio>();
				Collection<ProductCancelRatio> productCancelRatioList = new ArrayList<ProductCancelRatio>();
				
				ProductEntity product = new ProductEntity();
				ProductHighDiscountRatio productHighDiscountRatio = new ProductHighDiscountRatio();
				
				InsurerEntity insurer = insurerDao.findByShortName(sheet.getCell(0, i).getContents());

				//保險公司
				product.setInsurer(insurer);
				//商品名稱
				product.setLocalName(sheet.getCell(1, i).getContents());
				//年期
				product.setYear(Integer.parseInt(sheet.getCell(2, i).getContents()));
				//商品年度
				product.setYearCode(sheet.getCell(3, i).getContents());
				//商品代碼
				product.setCode(sheet.getCell(4, i).getContents());
				//幣別
				if("美元".equals(sheet.getCell(5, i).getContents())){
					product.setCurr(Currency.USD);
				}else if("新台幣".equals(sheet.getCell(5, i).getContents())){
					product.setCurr(Currency.TWD);
				};		
				//預定利率
				if("NON".equals(sheet.getCell(6, i).getContents())){
					product.setPredictInterestRate(null);
				}else{
					product.setPredictInterestRate(BigDecimal.valueOf(Double.parseDouble(sheet.getCell(6, i).getContents())));
				};
				//宣告利率
				if("NON".equals(sheet.getCell(7, i).getContents())){
					product.setDeclareInterestRate(null);
				}else{
					product.setDeclareInterestRate(BigDecimal.valueOf(Double.parseDouble(sheet.getCell(7, i).getContents().substring(0, sheet.getCell(7, i).getContents().length()-1))/100));
				};		
				//繳費方式
				product.setPaymentMethod(sheet.getCell(8, i).getContents());	
				//寫進資料庫
				ProductEntity productEntity = dao.save(product);
				//切換換第二頁 (基本保費)
				sheet = book.getSheet(1);
				//第三排開始往下讀
				for(int j=2;j<154;j++){
					ProductPremiumRatio productPremiumRatio = new ProductPremiumRatio();
					//設定商品ID
					productPremiumRatio.setProductId(productEntity.getId());
					//設定性別
					productPremiumRatio.setGender(sheet.getCell(7, j).getContents());
					//設定年紀
					productPremiumRatio.setInsAge(Integer.parseInt(sheet.getCell(8, j).getContents()));
					//設定費率
					productPremiumRatio.setPremiumRatio(BigDecimal.valueOf(Double.parseDouble(sheet.getCell(9, j).getContents())));
					//寫進基本費率 table
					productPremiumRatioDao.save(productPremiumRatio);
					
					productPremiumRatioList.add(productPremiumRatio);
				}
				productEntity.setPremiumRatios(productPremiumRatioList);
				
				//切換至第三頁 (違約金費率)
				sheet = book.getSheet(2);
				for(int k=2;k<154;k++){
					ProductCancelRatio productCancelRatio = new ProductCancelRatio();
					//設定商品ID
					productCancelRatio.setProductId(productEntity.getId());
					//違約金投保年齡
					if(sheet.getCell(8, k).getContents().substring(0,1)=="0"){
						productCancelRatio.setInsAge(Integer.parseInt(sheet.getCell(8, k).getContents().substring(1)));
					}else{
						productCancelRatio.setInsAge(Integer.parseInt(sheet.getCell(8, k).getContents()));						
					};
					//違約金性別
					productCancelRatio.setGender(sheet.getCell(7, k).getContents());
					//寫進違約金table
					productCancelRatioDao.save(productCancelRatio);
					
					productCancelRatioList.add(productCancelRatio);
				}
				productEntity.setCancelRatios(productCancelRatioList);
				//更新product table
				dao.save(productEntity);
				//高保費率開始
				//高保費率商品ID
//				productHighDiscountRatio.setProductId(productEntity.getId());
//				
//				//折扣趴數???????
////				sheet.getCell(14, i).getContents();
//				
				//下限
//				productHighDiscountRatio.setMinValue(BigDecimal.valueOf(Double.parseDouble(sheet.getCell(15, i).getContents())));
				//上限
//				productHighDiscountRatio.setMaxValue(Integer.parseInt(sheet.getCell(16, i).getContents()));
				//寫入高保費table
//				productHighDiscountRatioDao.save(productHighDiscountRatio);
//				
//				productHighDiscountRatioList.add(productHighDiscountRatio);
//				
//				productEntity.setHighDiscountRatios(productHighDiscountRatioList);
			}
			
			return true;
			
		} catch (BiffException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}