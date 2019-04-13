package indi.demo.flying.condition;

import org.apache.ibatis.type.StringTypeHandler;

import indi.demo.flying.pojo.Cart;
import indi.mybatis.flying.annotations.ConditionMapperAnnotation;
import indi.mybatis.flying.annotations.Or;
import indi.mybatis.flying.models.Conditionable;
import indi.mybatis.flying.models.Limitable;
import indi.mybatis.flying.models.Sortable;
import indi.mybatis.flying.statics.ConditionType;

public class CartCondition extends Cart implements Conditionable {

	private static final long serialVersionUID = 1L;

	private Limitable limiter;

	private Sortable sorter;

	@Override
	public Limitable getLimiter() {
		return limiter;
	}

	@Override
	public void setLimiter(Limitable limiter) {
		this.limiter = limiter;
	}

	@Override
	public Sortable getSorter() {
		return sorter;
	}

	@Override
	public void setSorter(Sortable sorter) {
		this.sorter = sorter;
	}

	@Or({ @ConditionMapperAnnotation(dbFieldName = "ID", conditionType = ConditionType.Equal),
			@ConditionMapperAnnotation(dbFieldName = "ID", conditionType = ConditionType.Equal) })
	private Object[] idOr;

	@Or({ @ConditionMapperAnnotation(dbFieldName = "ID", conditionType = ConditionType.Equal, customTypeHandler = StringTypeHandler.class),
			@ConditionMapperAnnotation(dbFieldName = "ID", conditionType = ConditionType.Equal, customTypeHandler = StringTypeHandler.class) })
	private Object[] personIdOr;

	public Object[] getIdOr() {
		return idOr;
	}

	public void setIdOr(Object... idOr) {
		this.idOr = idOr;
	}

	public Object[] getPersonIdOr() {
		return personIdOr;
	}

	public void setPersonIdOr(Object... personIdOr) {
		this.personIdOr = personIdOr;
	}

}
