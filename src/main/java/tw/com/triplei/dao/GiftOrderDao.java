package tw.com.triplei.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.GiftOrderEntity;

public interface GiftOrderDao extends GenericDao<GiftOrderEntity>{

	public GiftOrderEntity findById(long id);

	public Page<GiftOrderEntity> findByCreatedBy(String createdBy , Pageable arg);
	
}
