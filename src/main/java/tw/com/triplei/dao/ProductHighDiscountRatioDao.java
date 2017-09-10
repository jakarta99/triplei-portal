package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductHighDiscountRatio;

public interface ProductHighDiscountRatioDao extends GenericDao<ProductHighDiscountRatio>{
	
	public ProductHighDiscountRatio findById(Long id);

}
