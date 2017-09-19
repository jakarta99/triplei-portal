package tw.com.triplei.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.enums.Currency;

public interface ProductDao extends GenericDao<ProductEntity> {

	public ProductEntity findById(Long id);
	
//	@Query("select g.localName,g.code,g.insurer.name from ProductEntity g join g.premiumRatios p join g.cancelRatios c join g.highDiscountRatios h "
//			+ "where p.gender = :gender and p.insAge = :insAge and g.interestRateType = :interestRateType and g.year = :year "
//			+ "and c.gender = :gender and c.insAge = :insAge and g.curr = :currency")
//	public List<ProductEntity> search(@Param("gender")String gender,
//			@Param("insAge")int insAge,@Param("currency")Currency currency,
//			@Param("interestRateType")String interestRateType,@Param("year")int year);
	
	public List<ProductEntity> findByCurrAndInterestRateTypeAndYear(Currency currency,String interestRateType,int year);
}
