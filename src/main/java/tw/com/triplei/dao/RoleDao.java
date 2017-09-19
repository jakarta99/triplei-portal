package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;

public interface RoleDao extends GenericDao<RoleEntity>{
	
	public RoleEntity findByCode(String code);

}
