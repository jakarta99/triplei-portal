package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductPremiumRatio;

public interface ProductPremiumRatioDao extends GenericDao<ProductPremiumRatio>{
	
	public ProductPremiumRatio findById(Long id);
}
