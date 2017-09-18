package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.RecipientEntity;

public interface RecipientDao extends GenericDao<RecipientEntity> {

	public RecipientEntity findById(long id);

}
