package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.GiftOrderEntity;

public interface GiftOrderDao extends GenericDao<GiftOrderEntity>{

	public GiftOrderEntity findById(long id);
	
}
