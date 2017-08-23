package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.InsurerDao;
import tw.com.triplei.entity.InsurerEntity;

@Service
public class InsurerService extends GenericService<InsurerEntity> {

	@Autowired
	private InsurerDao dao;
	
	@Override
	public GenericDao<InsurerEntity> getDao() {
		return dao;
	}

	@Override
	public List<Message> validateInsert(InsurerEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InsurerEntity handleInsert(InsurerEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> validateUpdate(InsurerEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InsurerEntity handleUpdate(InsurerEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
