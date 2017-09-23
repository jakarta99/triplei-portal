package tw.com.triplei.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.searchbox.strings.StringUtils;
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

//	@Transactional
	@Override
	public List<Message> validateUpdate(UserEntity entity) {
		List<Message> messages = new ArrayList<Message>();
		
		//UserEntity dbEntity = userDao.findOne(entity.getId());
		
//		if (StringUtils.isBlank(entity.getName())) {
//			messages.add(Message.builder().code("orgPassword").value("原始欄位為必填欄位").build());
//		}
		
		
		log.debug("{}", messages);
		
		return messages;
	}
	
	@Transactional
	@Override
	public UserEntity handleUpdate(final UserEntity entity) {
		final UserEntity dbUserEntity = userDao.findOne(entity.getId());

		if(!StringUtils.isBlank(entity.getCheckPassword())){
			dbUserEntity.setPassword(encodePasswrod(entity.getPassword()));
			dbUserEntity.setOrgPassword(encodePasswrod(entity.getPassword()));
		}

		
		if(!StringUtils.isBlank(entity.getName())){
			dbUserEntity.setName(entity.getName());
		}
		
		if(!StringUtils.isBlank(entity.getEmail())){
			dbUserEntity.setEmail(entity.getEmail());
		}
		
		if(!StringUtils.isBlank(entity.getGender())){
			dbUserEntity.setGender(entity.getGender());
		}
		
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		dbUserEntity.setModifiedTime(timestamp);
		
		if(!StringUtils.isBlank(entity.getAccountNumber())){
			dbUserEntity.setModifiedBy(entity.getAccountNumber());
		}
		

		return dbUserEntity;
	}
	
	public String encodePasswrod(final String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
