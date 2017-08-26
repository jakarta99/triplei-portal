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
import tw.com.triplei.dao.GiftDao;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.InsurerEntity;

@Slf4j
@Service
public class GiftService extends GenericService<GiftEntity> {

	@Autowired
	private GiftDao giftDao;

	@Override
	public GenericDao<GiftEntity> getDao() {
		return giftDao;
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
}
