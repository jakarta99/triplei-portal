package tw.com.triplei.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.GiftDao;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.enums.GiftType;

@Slf4j
@Service
public class GiftService extends GenericService<GiftEntity> {


	@Autowired
	private GiftDao giftDao;

	@Override
	public GenericDao<GiftEntity> getDao() {
		return giftDao;
	}
	
	public List<GiftEntity> getType(String giftType){
		return giftDao.findByGiftType(giftType);
	}
	
	public List<GiftEntity> getTypeTop3(GiftType giftType){
		return giftDao.findTop3ByGiftTypeOrderByBonus(giftType);
	}
	
	public List<GiftEntity> getTypeHot(boolean hotGift){
		return giftDao.findByHotGift(hotGift);
	}
	
	public List<GiftEntity> getTypeTop3Hot(boolean hotGift){
		return giftDao.findTop3ByHotGift(hotGift);
	}


	@Override
	public List<Message> validateInsert(GiftEntity entity) {
		List<Message> messages = Lists.newArrayList();

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(GiftEntity entity) {
		List<Message> messages = Lists.newArrayList();

		GiftEntity dbEntity = giftDao.findById(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public GiftEntity handleUpdate(GiftEntity entity) {
		GiftEntity dbEntity = giftDao.findOne(entity.getId());
		
		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[]{"id","createdBy","createdTime"});
		
		return dbEntity;
	}
	
	@Transactional(readOnly = true)
	public GiftEntity getById(long id) {
		return giftDao.findById(id);
	}
	
	
	public String imageUpload(MultipartFile file) {
		byte[] bytes;
		if(file.toString()!=""){
		try {
			bytes = file.getBytes();
			String path = "src/main/webapp/userfiles/giftImage";
			String path1 = "/userfiles/giftImage";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();

			String date = DateTimeFormatter.ofPattern("MM-dd_HHmmss").format(LocalDateTime.now());
			File serverFile = new File(dir.getAbsolutePath() + File.separator + date + file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			String url = path1+"/"+date+file.getOriginalFilename();
			stream.write(bytes);
			stream.close();
			
			return url;
		} catch (IOException e) {
			e.printStackTrace();
			return "Upload Fail";
		}
		}
			return "";
	}
	
	
}
