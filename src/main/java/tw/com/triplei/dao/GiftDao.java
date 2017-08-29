package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.enums.GiftType;

public interface GiftDao extends GenericDao<GiftEntity>{

	public GiftEntity findById(long id);
	
	@Query("SELECT g FROM GiftEntity g WHERE GIFT_TYPE = :giftType")
	public List<GiftEntity> getType(@Param("giftType") GiftType giftType);
}
