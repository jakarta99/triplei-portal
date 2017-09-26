package tw.com.triplei.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.RecipientEntity;
import tw.com.triplei.entity.UserEntity;

public interface RecipientDao extends GenericDao<RecipientEntity> {

	public RecipientEntity findById(long id);
	
	public Collection<RecipientEntity> findByCreatedBy(String name);
	
	public Page<RecipientEntity> findByUser(UserEntity user, Pageable arg);
	
	public Page<RecipientEntity> findByCreatedBy(String name, Pageable arg);
}
