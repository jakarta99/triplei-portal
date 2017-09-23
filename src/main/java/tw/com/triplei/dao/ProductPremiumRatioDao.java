package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductCancelRatio;
import tw.com.triplei.entity.ProductPremiumRatio;

public interface ProductPremiumRatioDao extends GenericDao<ProductPremiumRatio>{
	
	public Page<ProductPremiumRatio> findByProductId(Long id, Pageable arg);
	
	public ProductPremiumRatio findById(Long id);
	
	public List<ProductPremiumRatio> findByProductIdAndInsAgeAndGender(Long productId,int insAge,String gender);
	
	public List<ProductPremiumRatio> findByProductId(Long id);
	
	@Query("DELETE FROM ProductPremiumRatio where PRODUCT_ID = :productId")
	@Modifying
	public void deleteByPorductId(@Param("productId")Long productId);
}
