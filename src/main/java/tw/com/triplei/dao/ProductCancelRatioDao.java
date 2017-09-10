package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductCancelRatio;

public interface ProductCancelRatioDao extends GenericDao<ProductCancelRatio> {
	public ProductCancelRatio findById(Long id);
}
