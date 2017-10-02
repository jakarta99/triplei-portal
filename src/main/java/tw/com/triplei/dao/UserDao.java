package tw.com.triplei.dao;

import java.util.Collection;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.UserEntity;

public interface UserDao extends GenericDao<UserEntity>{
	
	public UserEntity findByAccountNumber(String accountNumber);
	
	public UserEntity findByEmail(String email);
	
	public UserEntity findByRegisteredCode(String registeredCode);
	
	public Collection<UserEntity> findByName(String name);
	
	public UserEntity findByProviderUserId(String providerUserId);
}
