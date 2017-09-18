package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductCancelRatio;

public interface ProductCancelRatioDao extends GenericDao<ProductCancelRatio> {
	
	public Page<ProductCancelRatio> findByProductId(Long id, Pageable arg);
	
	public ProductCancelRatioDao findById(Long id);
	
	public List<ProductCancelRatio> findByProductIdAndInsAgeAndGender(Long productId,int insAge,String gender);

}
