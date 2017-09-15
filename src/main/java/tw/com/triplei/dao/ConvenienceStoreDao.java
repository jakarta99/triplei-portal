package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ConvenienceStoreEntity;

public interface ConvenienceStoreDao extends GenericDao<ConvenienceStoreEntity>{

	public ConvenienceStoreEntity findById(Long id);
	
}
