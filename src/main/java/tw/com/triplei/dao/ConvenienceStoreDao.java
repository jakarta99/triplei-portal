package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ConvenienceStoreEntity;

public interface ConvenienceStoreDao extends GenericDao<ConvenienceStoreEntity>{

	public ConvenienceStoreEntity findById(Long id);
	
	public ConvenienceStoreEntity findByAddress(String address);
	
	public ConvenienceStoreEntity findByCity(String city);

	public ConvenienceStoreEntity findByCityAndRegion(String city, String region);

	public ConvenienceStoreEntity findByCityAndRegionAndStreet(String city, String region, String street);
}
