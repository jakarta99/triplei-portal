package tw.com.triplei.dao;

import java.util.List;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ConvenienceStoreEntity;

public interface ConvenienceStoreDao extends GenericDao<ConvenienceStoreEntity>{

	public ConvenienceStoreEntity findById(Long id);
	
	public ConvenienceStoreEntity findByAddress(String address);
	
	public List<ConvenienceStoreEntity> findByCity(String city);

	public List<ConvenienceStoreEntity> findByCityAndRegion(String city, String region);

	public List<ConvenienceStoreEntity> findByCityAndRegionAndStreet(String city, String region, String street);
}
