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
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.ArticleDao;
import tw.com.triplei.entity.ArticleEntity;

@Slf4j
@Service
public class ArticleService extends GenericService<ArticleEntity> {

	@Autowired
	private ArticleDao dao;
	
	@Override
	public GenericDao<ArticleEntity> getDao() {
		return dao;
	}

	@Override
	public List<Message> validateInsert(ArticleEntity entity) {

		List<Message> messages = Lists.newArrayList();
		
		ArticleEntity dbEntity = dao.findById(entity.getId());
		
		if(dbEntity != null) {
			messages.add(Message.builder().code("code").value("ID不可重複").build());
		}
		
		log.debug("{}", messages);
		
		return messages;
	}


	@Override
	public List<Message> validateUpdate(ArticleEntity entity) {
		
		List<Message> messages = Lists.newArrayList();
		
		ArticleEntity dbEntity = dao.findOne(entity.getId());
		
		if(dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}
		
		log.debug("{}", messages);
		
		return messages;
	}

	@Override
	public ArticleEntity handleUpdate(ArticleEntity entity) {
		ArticleEntity dbEntity = dao.findOne(entity.getId());
		
		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[]{"id","code","createdBy","createdTime"});
		
		return dbEntity;
	}
	
	public String imageUpload(MultipartFile file) {
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String path = "src/main/resources/ArticleContent/bannerImage";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();

			String date = DateTimeFormatter.ofPattern("MM-dd_HHmmss").format(LocalDateTime.now());
			File serverFile = new File(dir.getAbsolutePath() + File.separator + date + file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			String url = path+"/"+date+file.getOriginalFilename();
			stream.write(bytes);
			stream.close();
			
			return url;
		} catch (IOException e) {
			e.printStackTrace();
			return "Upload Fail";
		}
	}
	
}
