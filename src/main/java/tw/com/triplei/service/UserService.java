package tw.com.triplei.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.UserEntity;

@Slf4j
@Service
public class UserService extends GenericService<UserEntity> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;

	@Override
	public GenericDao<UserEntity> getDao() {
		return userDao;
	}

	@Override
	public List<Message> validateInsert(UserEntity entity) {
		List<Message> messages = new ArrayList<Message>();
		
		return messages;
	}

	@Transactional
	@Override
	public List<Message> validateUpdate(UserEntity entity) {
		List<Message> messages = new ArrayList<Message>();
		
		return messages;
	}
	
	@Transactional
	@Override
	public UserEntity handleUpdate(final UserEntity entity) {
		final UserEntity dbUserEntity = userDao.findOne(entity.getId());
		//XXX 註冊會從adminUserService 跑到這邊 update 一次，已加密過不須再加密
		//dbUserEntity.setPassword(encodePasswrod(entity.getPassword()));
//		dbUserEntity.setPassword(dbUserEntity.getPassword());

		
//		dbUserEntity.setCheckPassword(dbUserEntity.getCheckPassword());
//		dbUserEntity.setEnabled(dbUserEntity.getEnabled());
//		dbUserEntity.setRoles(dbUserEntity.getRoles());
		
		dbUserEntity.setName(entity.getName());
		dbUserEntity.setEmail(entity.getEmail());
		dbUserEntity.setGender(entity.getGender());
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		dbUserEntity.setModifiedTime(timestamp);
		dbUserEntity.setModifiedBy(entity.getAccountNumber());

		return dbUserEntity;
	}
	
	public String encodePasswrod(final String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
