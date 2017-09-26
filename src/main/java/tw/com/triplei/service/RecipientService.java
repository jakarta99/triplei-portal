package tw.com.triplei.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.RecipientDao;
import tw.com.triplei.entity.RecipientEntity;

@Slf4j
@Service
public class RecipientService extends GenericService<RecipientEntity> {

	@Autowired
	private RecipientDao dao;

	@Override
	public RecipientDao getDao() {
		return dao;
	}
	
//	public Collection<RecipientEntity> findByUserName(String name){
//		return dao.findByCreatedBy(name);
//	}
	
	@Override
	public List<Message> validateInsert(RecipientEntity entity) {
		List<Message> messages = Lists.newArrayList();

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(RecipientEntity entity) {

		List<Message> messages = Lists.newArrayList();

		RecipientEntity dbEntity = dao.findOne(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public RecipientEntity handleUpdate(RecipientEntity entity) {
		RecipientEntity dbEntity = dao.findOne(entity.getId());

		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[] { "id", "createdBy", "createdTime" });

		return dbEntity;
	}

	@Transactional(readOnly = true)
	public RecipientEntity getById(long id) {
		return dao.findById(id);
	}
	
}
