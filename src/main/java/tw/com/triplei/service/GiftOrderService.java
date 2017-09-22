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
import tw.com.triplei.dao.GiftOrderDao;
import tw.com.triplei.entity.GiftOrderEntity;

@Slf4j
@Service
public class GiftOrderService extends GenericService<GiftOrderEntity> {


	@Autowired
	private GiftOrderDao giftOrderDao;

	@Override
	public GenericDao<GiftOrderEntity> getDao() {
		return giftOrderDao;
	}
	
	@Override
	public List<Message> validateInsert(GiftOrderEntity entity) {
		List<Message> messages = Lists.newArrayList();

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(GiftOrderEntity entity) {
		List<Message> messages = Lists.newArrayList();

		GiftOrderEntity dbEntity = giftOrderDao.findById(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public GiftOrderEntity handleUpdate(GiftOrderEntity entity) {
		GiftOrderEntity dbEntity = giftOrderDao.findOne(entity.getId());
		
		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[]{"id","createdBy","createdTime"});
		
		return dbEntity;
	}
	
	@Transactional(readOnly = true)
	public GiftOrderEntity getById(long id) {
		return giftOrderDao.findById(id);
	}
	

	
}
