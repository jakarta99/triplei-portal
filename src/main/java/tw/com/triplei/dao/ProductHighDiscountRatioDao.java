package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductHighDiscountRatio;

public interface ProductHighDiscountRatioDao extends GenericDao<ProductHighDiscountRatio>{
	
	public Page<ProductHighDiscountRatio> findByProductId(Long id, Pageable arg);
	
	public ProductHighDiscountRatio findById(Long id);
	
	public List<ProductHighDiscountRatio> findByProductId(Long id);
	
	@Query("DELETE FROM ProductHighDiscountRatio where PRODUCT_ID = :productId")
	@Modifying
	public void deleteByPorductId(@Param("productId")Long productId);

}
