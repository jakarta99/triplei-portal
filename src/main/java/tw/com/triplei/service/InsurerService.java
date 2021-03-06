package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.InsurerDao;
import tw.com.triplei.entity.InsurerEntity;

@Slf4j
@Service
public class InsurerService extends GenericService<InsurerEntity> {

	@Autowired
	private InsurerDao dao;

	@Override
	public GenericDao<InsurerEntity> getDao() {
		return dao;
	}

	@Override
	public List<Message> validateInsert(InsurerEntity entity) {

		List<Message> messages = Lists.newArrayList();

		InsurerEntity dbEntity = dao.findByCode(entity.getCode());

		if (dbEntity != null) {
			messages.add(Message.builder().code("code").value("代號不得重複").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(InsurerEntity entity) {

		List<Message> messages = Lists.newArrayList();

		InsurerEntity dbEntity = dao.findOne(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public InsurerEntity handleUpdate(InsurerEntity entity) {
		InsurerEntity dbEntity = dao.findOne(entity.getId());

		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[] { "id", "code", "createdBy", "createdTime" });

		return dbEntity;
	}

	@Transactional(readOnly = true)
	public InsurerEntity getByCode(String code) {
		return dao.findByCode(code);
	}
	
}
