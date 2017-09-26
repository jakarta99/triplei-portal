package tw.com.triplei.commons;


import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SpecCriterion {

	private QueryOpType op;

	private String field;

	private Object value;

	private List<SpecCriterion> orCriterions;


	public SpecCriterion(final QueryOpType op, final String field, final Object value) {
		this.op = op;
		this.field = field;
		this.value = value;
	}

	public SpecCriterion(final List<SpecCriterion> orCriterions) {
		this.orCriterions = orCriterions;
	}



	public QueryOpType getOp() {
		return op;
	}

	public void setOp(final QueryOpType op) {
		this.op = op;
	}

	public String getField() {
		return field;
	}

	public void setField(final String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public List<SpecCriterion> getOrCriterions() {
		return orCriterions;
	}

	public void setOrCriterions(final List<SpecCriterion> orCriterions) {
		this.orCriterions = orCriterions;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
