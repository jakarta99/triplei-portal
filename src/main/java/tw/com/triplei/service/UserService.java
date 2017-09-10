package tw.com.triplei.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.RoleDao;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;

@Slf4j
@Service
public class UserService extends GenericService<UserEntity>{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public GenericDao<UserEntity> getDao() {
		return userDao;
	}

	@Override
	public List<Message> validateInsert(UserEntity entity) {
		List<Message> messages = new ArrayList<Message>();

		UserEntity dbEntity = userDao.findByAccountNumber(entity.getAccountNumber());

		if (dbEntity != null) {
			messages.add(Message.builder().code("accountNumber").value("帳號不得重複").build());
		}

		log.debug("{}", messages);
		
		return messages;
	}

	@Override
	public List<Message> validateUpdate(UserEntity entity) {
		
		List<Message> messages = new ArrayList<Message>();

		UserEntity dbEntity = userDao.findOne(entity.getId());

//		if (dbEntity != null) {
//			messages.add(Message.builder().code("code").value("代號不得重複").build());
//		}

		log.debug("{}", messages);
		
		return messages;
	}
	
	
	//@Transactional
	@org.springframework.transaction.annotation.Transactional
	@Override
	public UserEntity handleUpdate(final UserEntity entity) {
		final UserEntity dbUserEntity = userDao.findOne(entity.getId());
		
		//dbUserEntity.setPassword(encodePasswrod(entity.getPassword()));
		dbUserEntity.setEmail(entity.getEmail());
		dbUserEntity.setActive(entity.getActive());
		
		dbUserEntity.getRoles().clear();
		
		for (final RoleEntity role : entity.getRoles()) {
			final RoleEntity dbRoleEntity = roleDao.getOne(role.getId());
			dbUserEntity.getRoles().add(dbRoleEntity);
		}

		
		return dbUserEntity;
	}

}
