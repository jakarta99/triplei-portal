package tw.com.triplei.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.ConvenienceStoreDao;
import tw.com.triplei.entity.ConvenienceStoreEntity;

@Slf4j
@Service
public class ConvenienceStoreService extends GenericService<ConvenienceStoreEntity> {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ConvenienceStoreDao convenienceStoreDao;

	private String url;

	private BigDecimal bigDecimal;

	public int stringToAge(String string) {
		int number = Integer.parseInt(string);
		return number;
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

	@Override
	public GenericDao<ConvenienceStoreEntity> getDao() {
		return convenienceStoreDao;
	}

	@Override
	public List<Message> validateInsert(ConvenienceStoreEntity entity) {

		List<Message> messages = Lists.newArrayList();

		ConvenienceStoreEntity dbEntity = convenienceStoreDao.findById(entity.getId());

		if (dbEntity != null) {
			messages.add(Message.builder().code("code").value("ID不可重複").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(ConvenienceStoreEntity entity) {

		List<Message> messages = Lists.newArrayList();

		ConvenienceStoreEntity dbEntity = convenienceStoreDao.findOne(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public ConvenienceStoreEntity handleUpdate(ConvenienceStoreEntity entity) {
		ConvenienceStoreEntity dbEntity = convenienceStoreDao.findOne(entity.getId());

		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[] { "id", "code", "createdBy", "createdTime" });

		return dbEntity;
	}

	public boolean FileUpload(MultipartFile file) throws Exception {
		BufferedOutputStream stream = null;
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String path = env.getProperty("convenienceStoreFileUploadPath");
			log.debug("ConveniencecStore Upload path{}",path);
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

			// 從第2排開始往下讀
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row rowi = sheet.getRow(i);
				try {
					if (rowi.getCell(0).getStringCellValue() == "") {
						continue;
					}
				} catch (Exception e) {
//					log.debug("sheet.getLastRowNum()  {}",sheet.getLastRowNum());
					continue;
				}
//				log.debug("iiiii = {}",i);
				ConvenienceStoreEntity convenienceStoreEntity = new ConvenienceStoreEntity();

				// 廠商
				convenienceStoreEntity.setManufacturer(rowi.getCell(0).getStringCellValue());
				// 店名
				convenienceStoreEntity.setStoreName(rowi.getCell(1).getStringCellValue());
				// 縣市
				convenienceStoreEntity.setCity(rowi.getCell(2).getStringCellValue());
				// 區域
				convenienceStoreEntity.setRegion(rowi.getCell(3).getStringCellValue());
				// 路名
				convenienceStoreEntity.setStreet(rowi.getCell(4).getStringCellValue());
				// 地址
				convenienceStoreEntity.setAddress(rowi.getCell(5).getStringCellValue());

				// 寫進資料庫
				ConvenienceStoreEntity convenienceStoreEntity2 = convenienceStoreDao.save(convenienceStoreEntity);
//				log.debug("jjjj  {}",convenienceStoreEntity2);
//				System.out.println(convenienceStoreEntity2);
			}
//			log.debug("total {}",sheet.getLastRowNum());
		}

		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public List<ConvenienceStoreEntity> findByCity(String city) {
		return convenienceStoreDao.findByCity(city);
		
	}
	public List<ConvenienceStoreEntity> findByCityAndRegion(String city, String region) {
		return convenienceStoreDao.findByCityAndRegion(city, region);
		
	}
	public List<ConvenienceStoreEntity> findByCityAndRegionAndStreet(String city, String region, String street) {
		return convenienceStoreDao.findByCityAndRegionAndStreet(city, region, street);
		
	}

	public ConvenienceStoreEntity findByAddress(String address) {
		return convenienceStoreDao.findByAddress(address);
		
	}
}