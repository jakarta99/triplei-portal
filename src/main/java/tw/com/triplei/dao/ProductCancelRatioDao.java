package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductCancelRatio;

public interface ProductCancelRatioDao extends GenericDao<ProductCancelRatio> {
	
//	public Page<ProductCancelRatio> findByProduct_Id(Long id,Specification<ProductCancelRatio> arg0, Pageable arg1);
	
	public ProductCancelRatioDao findById(Long id);
}
