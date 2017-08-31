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
	public List<GiftEntity> findByGiftType(@Param("giftType")String giftType);
	
//	@Query("SELECT g FROM GiftEntity g WHERE GIFT_TYPE = :giftType order by g.bonus")
//	public List<GiftEntity> findByGiftTypeTop3(@Param("giftType")String giftType);
//	 public List<GiftEntity> findTop3ByGiftTypeOrderByBonusDesc(@Param("giftType")String giftType);
	public List<GiftEntity> findTop3ByGiftTypeOrderByBonus(GiftType giftType);
	
	@Query("SELECT g FROM GiftEntity g WHERE HOT_GIFT = :hotGift")
	public List<GiftEntity> findByHotGift(@Param("hotGift")boolean hotGift);
	
	public List<GiftEntity> findTop3ByHotGift(boolean hotGift);
}
