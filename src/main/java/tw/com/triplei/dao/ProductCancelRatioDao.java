package tw.com.triplei.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductCancelRatio;

public interface ProductCancelRatioDao extends GenericDao<ProductCancelRatio> {
	
	public Page<ProductCancelRatio> findByProductId(Long id, Pageable arg);
	
	public ProductCancelRatioDao findById(Long id);

}
