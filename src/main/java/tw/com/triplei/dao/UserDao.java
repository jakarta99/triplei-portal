package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.UserEntity;

public interface UserDao extends GenericDao<UserEntity>{
	
	public UserEntity findByAccountNumber(String accountNumber);
	
	public UserEntity findByEmail(String email);
	
	public UserEntity findByRegisteredCode(String registeredCode);
	
	public UserEntity findByName(String name);
}
