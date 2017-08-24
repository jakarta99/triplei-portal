package tw.com.triplei.admin.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tw.com.triplei.entity.InsurerEntity;

public class InsurerSpecification implements Specification<InsurerEntity> {

	@Override
	public Predicate toPredicate(Root<InsurerEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return null;
	}

}
