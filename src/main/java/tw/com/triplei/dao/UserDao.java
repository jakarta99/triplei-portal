package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.UserEntity;

public interface UserDao extends GenericDao<UserEntity>{
	
	public UserEntity findByAccountNumber(String accountNumber);
	
	public UserEntity findByName(String name);
}
