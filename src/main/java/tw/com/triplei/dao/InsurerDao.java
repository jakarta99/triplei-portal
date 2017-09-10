package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.InsurerEntity;

public interface InsurerDao extends GenericDao<InsurerEntity> {

	public InsurerEntity findByCode(String code);
	
	public InsurerEntity findByShortName(String shortName);
}
