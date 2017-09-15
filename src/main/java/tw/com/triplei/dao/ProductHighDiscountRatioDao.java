package tw.com.triplei.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductHighDiscountRatio;
import tw.com.triplei.entity.ProductPremiumRatio;

public interface ProductHighDiscountRatioDao extends GenericDao<ProductHighDiscountRatio>{
	
	public Page<ProductHighDiscountRatio> findByProductId(Long id, Pageable arg);
	
	public ProductHighDiscountRatio findById(Long id);

}
