package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ConvenienceStoreEntity;

public interface ConvenienceStoreDao extends GenericDao<ConvenienceStoreEntity>{

	public ConvenienceStoreEntity findById(Long id);
	
	public ConvenienceStoreEntity findByAddress(String address);
	
//	public List<ConvenienceStoreEntity> findByCity(String city);
	
	@Query("select DISTINCT s.region from ConvenienceStoreEntity s where s.city = ?1")
	public List<ConvenienceStoreEntity> findByCity(String city);
	
	@Query("select DISTINCT s.street from ConvenienceStoreEntity s where s.city = ?1 and region = ?2")
	public List<ConvenienceStoreEntity> findByCityAndRegion(String city, String region);

	public List<ConvenienceStoreEntity> findByCityAndRegionAndStreet(String city, String region, String street);
}
