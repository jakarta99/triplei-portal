package tw.com.triplei.admin.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.entity.QuestionEntity;

public class QuestionSpecification implements Specification<QuestionEntity> {

	@Override
	public Predicate toPredicate(Root<QuestionEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return null;
	}


}
