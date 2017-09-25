package tw.com.triplei.admin.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tw.com.triplei.entity.GiftOrderEntity;

public class GiftOrderSpecification implements Specification<GiftOrderEntity> {

	@Override
	public Predicate toPredicate(Root<GiftOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return null;
	}


}
