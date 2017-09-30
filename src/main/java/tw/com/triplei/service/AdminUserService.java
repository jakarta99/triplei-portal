package tw.com.triplei.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		
		if(StringUtils.isBlank(entity.getProviderUserId())){
			// 一般會員註冊
			UserEntity dbEntity = userDao.findByEmail(entity.getEmail()); // email 不得重複註冊
			
			if (StringUtils.isBlank(entity.getName())) {
				messages.add(Message.builder().code("name").value("會員姓名為必填欄位").build());
			}

			if (StringUtils.isBlank(entity.getEmail())) {
				messages.add(Message.builder().code("email").value("電子信箱為必填欄位").build());
			}

			if (dbEntity != null) {
				messages.add(Message.builder().code("email").value("該電子信箱已註冊").build());
			}

			if (StringUtils.isBlank(entity.getPassword())) {
				messages.add(Message.builder().code("password").value("請輸入密碼").build());
			}

			if (StringUtils.isBlank(entity.getCheckPassword())) {
				messages.add(Message.builder().code("checkPassword").value("請輸入確認密碼").build());
			}

			if (!StringUtils.isBlank(entity.getPassword()) && !StringUtils.isBlank(entity.getCheckPassword())) {
				if (!entity.getPassword().equals(entity.getCheckPassword())) {
					messages.add(Message.builder().code("password").value("輸入的密碼不相同，請重新輸入").build());
					messages.add(Message.builder().code("checkPassword").value("輸入的密碼不相同，請重新輸入").build());
				}
			}
			


			log.debug("{}", messages);
		}
		

		
		return messages;
	}

	@Override
	public List<Message> validateUpdate(UserEntity entity) {
		
		List<Message> messages = new ArrayList<Message>();
		log.debug("{}", messages);
		
		return messages;
	}
	
	@Transactional
	//@org.springframework.transaction.annotation.Transactional
	@Override
	public UserEntity handleInsert(final UserEntity entity) {
		
		entity.setPassword(encodePasswrod(entity.getPassword()));
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		entity.setCreatedTime(timestamp);
		entity.setCreatedBy(entity.getAccountNumber());
		
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
		// 後台會員管理不能更改密碼，密碼不須加密
//		dbUserEntity.setPassword(dbUserEntity.getPassword());
		dbUserEntity.setEmail(entity.getEmail());
		dbUserEntity.setEnabled(entity.getEnabled());
		if(!StringUtils.isBlank(entity.getRegisteredCode())){
			dbUserEntity.setRegisteredCode(entity.getRegisteredCode());
		}
		
		dbUserEntity.setRemainPoint(entity.getRemainPoint());
		dbUserEntity.setAudittingPoint(entity.getAudittingPoint());
		dbUserEntity.setExchangedPoint(entity.getExchangedPoint());
		

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
	
	@Transactional
	public UserEntity updateForgetPassword(final UserEntity entity) {
		final UserEntity dbUserEntity = userDao.findOne(entity.getId());
		
//		String newPassword = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4);
//		log.debug("newPassword : {}", newPassword);
//		dbUserEntity.setCheckPassword(newPassword); // 要記起未加密的密碼，寄給使用者，在使用者更新密碼後清空
//		dbUserEntity.setPassword(encodePasswrod(newPassword));
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);
		dbUserEntity.setModifiedTime(timestamp);
		dbUserEntity.setModifiedBy("forgetPassword");
		
		userDao.save(dbUserEntity);

		return dbUserEntity;
	}
	
	public String encodePasswrod(final String rawPassword) {
		if(!StringUtils.isBlank(rawPassword)){
			return passwordEncoder.encode(rawPassword);
		} else {
			return null;
		}
	}
	
	public UserEntity getByEmail(final String email) {
		return userDao.findByEmail(email);
	}
	
	public UserEntity getByRegisteredCode(final String registeredCode) {
		return userDao.findByRegisteredCode(registeredCode);
	}
	
	public UserEntity getByAccountNumber(final String accountNumber) {
		return userDao.findByAccountNumber(accountNumber);
	}
	
	public UserEntity save(UserEntity entity){
		return userDao.save(entity);
	}
	

}
