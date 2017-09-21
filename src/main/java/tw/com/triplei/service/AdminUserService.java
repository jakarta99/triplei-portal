package tw.com.triplei.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AdminUserService extends GenericService<UserEntity>{
	
//	@Autowired
//	private static PasswordEncoder passwordEncoder;
	
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

		UserEntity dbEntity = userDao.findByEmail(entity.getEmail()); // email 不得重複註冊
		
		if (StringUtils.isBlank(entity.getName())) {
			messages.add(Message.builder().code("name").value("會員姓名為必填欄位").build());
		}

		if (dbEntity != null) {
			messages.add(Message.builder().code("email").value("該電子信箱已註冊").build());
		}
		
		if (!entity.getPassword().equals(entity.getCheckPassword())){
			messages.add(Message.builder().code("password").value("輸入的密碼不相同，請重新輸入").build());
			messages.add(Message.builder().code("checkpassword").value("輸入的密碼不相同，請重新輸入").build());
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
	
	@Transactional
	//@org.springframework.transaction.annotation.Transactional
	@Override
	public UserEntity handleInsert(final UserEntity entity) {
		
//		entity.setPassword(encodePasswrod(entity.getPassword()));
		entity.setPassword(entity.getPassword());
		entity.setPassword(entity.getCheckPassword());
		entity.setEnabled(false);  // 預設新註冊的會員不啟用
		entity.setAccountNumber(entity.getEmail());  // 帳號預設為電子信箱
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		entity.setCreatedTime(timestamp);
		
		final Set<RoleEntity> RoleEntity = new HashSet<>();
		final RoleEntity roleDefault = roleDao.findByCode("ROLE_NORMAL"); // 預設權限為一般會員
		RoleEntity.add(roleDefault);
		
		entity.setRoles(RoleEntity);
	
		return entity;
	}

	@Transactional
	//@org.springframework.transaction.annotation.Transactional
	@Override
	public UserEntity handleUpdate(final UserEntity entity) {
		final UserEntity dbUserEntity = userDao.findOne(entity.getId());
		
//		dbUserEntity.setPassword(encodePasswrod(entity.getPassword()));
		dbUserEntity.setPassword(entity.getPassword());
		dbUserEntity.setCheckPassword(entity.getCheckPassword());
		dbUserEntity.setEmail(entity.getEmail());
		dbUserEntity.setEnabled(entity.getEnabled());
		dbUserEntity.setCreatedTime(entity.getCreatedTime());
		

		dbUserEntity.getRoles().clear();
		
		// 每個會員至少會有一般會員權限		
		if(entity.getRoles().isEmpty()){
			final Set<RoleEntity> RoleEntity = new HashSet<>();
			final RoleEntity roleDefault = roleDao.findByCode("ROLE_NORMAL"); // 預設權限為一般會員
			RoleEntity.add(roleDefault);
			
			dbUserEntity.setRoles(RoleEntity);
		}else{
			for (final RoleEntity role : entity.getRoles()) {
				final RoleEntity dbRoleEntity = roleDao.getOne(role.getId());
				dbUserEntity.getRoles().add(dbRoleEntity);
			}
		}
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		dbUserEntity.setModifiedTime(timestamp);
		dbUserEntity.setModifiedBy(entity.getAccountNumber());

		return dbUserEntity;
	}
	
//	public String encodePasswrod(final String rawPassword) {
//		return passwordEncoder.encode(rawPassword);
//	}
	

}
