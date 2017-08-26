package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.GiftEntity;

public interface GiftDao extends GenericDao<GiftEntity>{

	public GiftEntity findById(long id);
}
