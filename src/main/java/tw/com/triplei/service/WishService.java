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
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.dao.WishDao;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.entity.WishEntity;

@Slf4j
@Service
public class WishService extends GenericService<WishEntity> {

	@Autowired
	private WishDao dao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public GenericDao<WishEntity> getDao() {
		return dao;
	}

	@Override
	public List<Message> validateInsert(WishEntity entity) {

		List<Message> messages = Lists.newArrayList();


		log.debug("{}", messages);

		return messages;
		// return null;
	}
	
	public boolean makeAWish(String accountNumber){
		UserEntity user = userDao.findByAccountNumber(accountNumber);
		if(user.getRemainWishTimes()){
			user.setRemainWishTimes(false);
			userDao.save(user);
			return true;
		}else	
			return false;
	}

	@Override
	public List<Message> validateUpdate(WishEntity entity) {
		List<Message> messages = Lists.newArrayList();

		WishEntity dbEntity = dao.findById(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}
	@Override
	public WishEntity handleUpdate(WishEntity entity) {
		WishEntity dbEntity = dao.findOne(entity.getId());
		
		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[]{"id","createdBy","createdTime"});
		
		return dbEntity;
	}
	
	@Transactional(readOnly = true)
	public WishEntity getById(long id) {
		return dao.findById(id);
	}


}
