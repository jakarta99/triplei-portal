package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductCancelRatio;

public interface ProductCancelRatioDao extends GenericDao<ProductCancelRatio> {
	
	public Page<ProductCancelRatio> findByProductId(Long id, Pageable arg);
	
	public ProductCancelRatioDao findById(Long id);
	
	public List<ProductCancelRatio> findByProductIdAndInsAgeAndGender(Long productId,int insAge,String gender);
	
	public List<ProductCancelRatio> findByProductId(Long id);
	
	@Query("DELETE FROM ProductCancelRatio where PRODUCT_ID = :productId")
	@Modifying
	public void deleteByPorductId(@Param("productId")Long productId);

}
