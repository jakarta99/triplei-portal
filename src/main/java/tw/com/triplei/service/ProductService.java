package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.ProductDao;
import tw.com.triplei.entity.ProductEntity;


@Slf4j
@Service
public class ProductService extends GenericService<ProductEntity> {

	@Autowired
	private ProductDao dao;
	
	@Override
	public GenericDao<ProductEntity> getDao() {
		return dao;
	}

	@Override
	public List<Message> validateInsert(ProductEntity entity) {

		List<Message> messages = Lists.newArrayList();
		
		ProductEntity dbEntity = dao.findById(entity.getId());
		
		if(dbEntity != null) {
			messages.add(Message.builder().code("code").value("ID不可重複").build());
		}
		
		log.debug("{}", messages);
		
		return messages;
	}


	@Override
	public List<Message> validateUpdate(ProductEntity entity) {
		
		List<Message> messages = Lists.newArrayList();
		
		ProductEntity dbEntity = dao.findOne(entity.getId());
		
		if(dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}
		
		log.debug("{}", messages);
		
		return messages;
	}

	@Override
	public ProductEntity handleUpdate(ProductEntity entity) {
		ProductEntity dbEntity = dao.findOne(entity.getId());
		
		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[]{"id","code","createdBy","createdTime"});
		
		return dbEntity;
	}
	
}