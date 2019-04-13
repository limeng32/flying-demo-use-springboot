package indi.demo.flying.condition;

import indi.demo.flying.pojo.Person;
import indi.demo.flying.pojo.Role;
import indi.mybatis.flying.annotations.ConditionMapperAnnotation;
import indi.mybatis.flying.annotations.Or;
import indi.mybatis.flying.models.Conditionable;
import indi.mybatis.flying.models.Limitable;
import indi.mybatis.flying.models.Sortable;
import indi.mybatis.flying.statics.ConditionType;

public class RoleCondition extends Role implements Conditionable {

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

	@Or({ @ConditionMapperAnnotation(dbFieldName = "VALUE", conditionType = ConditionType.Equal),
			@ConditionMapperAnnotation(dbFieldName = "VALUE", conditionType = ConditionType.Equal) })
	private Object[] value1OrValue2;

	@Or({ @ConditionMapperAnnotation(dbFieldName = "VALUE", conditionType = ConditionType.Equal),
			@ConditionMapperAnnotation(dbFieldName = "NAME", conditionType = ConditionType.Equal, subTarget = Person.class) })
	private Object[] valueOrPersonName;

	public Object[] getValue1OrValue2() {
		return value1OrValue2;
	}

	public void setValue1OrValue2(Object... value1OrValue2) {
		this.value1OrValue2 = value1OrValue2;
	}

	public Object[] getValueOrPersonName() {
		return valueOrPersonName;
	}

	public void setValueOrPersonName(Object... valueOrPersonName) {
		this.valueOrPersonName = valueOrPersonName;
	}

}
