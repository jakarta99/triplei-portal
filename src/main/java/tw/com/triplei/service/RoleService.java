package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.RoleDao;
import tw.com.triplei.entity.RoleEntity;

@Service
public class RoleService extends GenericService<RoleEntity>{

	@Autowired
	private RoleDao dao;
	
	@Override
	public GenericDao<RoleEntity> getDao() {
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

}