package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductPremiumRatio;

public interface ProductPremiumRatioDao extends GenericDao<ProductPremiumRatio>{
	
	public Page<ProductPremiumRatio> findByProductId(Long id, Pageable arg);
	
	public ProductPremiumRatio findById(Long id);
	
	public List<ProductPremiumRatio> findByProductIdAndInsAgeAndGender(Long productId,int insAge,String gender);
}
