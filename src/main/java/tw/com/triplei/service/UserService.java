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
		
		UserEntity dbEntity = userDao.findOne(entity.getId());
		
		// 更新個人資訊
		if(entity.getEditState().equals("info")){
			if(StringUtils.isBlank(entity.getName())){
				messages.add(Message.builder().code("name").value("會員姓名為必填欄位").build());
			}
			
			if(StringUtils.isBlank(entity.getEmail())){
				messages.add(Message.builder().code("email").value("電子信箱為必填欄位").build());
			}
		}

		// 更新密碼
		if(entity.getEditState().equals("pw")){
			if (StringUtils.isBlank(entity.getOrgPassword())) {
				messages.add(Message.builder().code("orgPassword").value("原始欄位為必填欄位").build());
			}
			
			if(!passwordEncoder.matches(entity.getOrgPassword(), dbEntity.getPassword())){
				messages.add(Message.builder().code("orgPassword").value("與原始密碼不符").build());
			}
			
			if (StringUtils.isBlank(entity.getPassword())) {
				messages.add(Message.builder().code("password").value("請輸入新密碼").build());
			}
			
			if (StringUtils.isBlank(entity.getOrgPassword())) {
				messages.add(Message.builder().code("checkPassword").value("請輸入確認密碼").build());
			}
			
			
			if (!StringUtils.isBlank(entity.getPassword()) && !StringUtils.isBlank(entity.getCheckPassword())){
				if (!entity.getPassword().equals(entity.getCheckPassword())){
					messages.add(Message.builder().code("password").value("輸入的密碼不相同，請重新輸入").build());
					messages.add(Message.builder().code("checkPassword").value("輸入的密碼不相同，請重新輸入").build());
				}
			}
		}
		
		
		log.debug("{}", messages);
		
		return messages;
	}
	
	@Transactional
	@Override
	public UserEntity handleUpdate(final UserEntity entity) {
		final UserEntity dbUserEntity = userDao.findOne(entity.getId());

		if(!StringUtils.isBlank(entity.getCheckPassword())){
			dbUserEntity.setPassword(encodePasswrod(entity.getPassword()));
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
