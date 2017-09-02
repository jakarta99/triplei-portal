package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ProductEntity;

public interface ProductDao extends GenericDao<ProductEntity> {

	public ProductEntity findById(Long id);
}
