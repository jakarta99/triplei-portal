package tw.com.triplei.admin.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tw.com.triplei.entity.UserEntity;

public class UserSpecification implements Specification<UserEntity> {

	@Override
	public Predicate toPredicate(Root<UserEntity> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
		return null;
	}

}
