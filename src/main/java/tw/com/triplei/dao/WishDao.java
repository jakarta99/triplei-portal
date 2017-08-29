package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.WishEntity;

public interface WishDao extends GenericDao<WishEntity> {

	public WishEntity findById(long Id);

}
