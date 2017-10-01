package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.RoleDao;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;

@Service
public class RoleService extends GenericService<RoleEntity>{

	@Autowired
	private RoleDao dao;
	
	@Override
	public RoleDao getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public List<Message> validateInsert(RoleEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> validateUpdate(RoleEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RoleEntity getByCode(final String code) {
		return dao.findByCode(code);
	}

}
