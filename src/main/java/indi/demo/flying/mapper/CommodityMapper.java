package indi.demo.flying.mapper;

import java.util.Collection;

import indi.demo.flying.pojo.Commodity;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Commodity.class })
public interface CommodityMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Commodity selectForAssociation(Object id);
	
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Commodity mySelect(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Commodity> mySelectAll(Commodity t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Commodity mySelectOne(Commodity t);

	public void myInsert(Commodity t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdate(Commodity t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdatePersistent(Commodity t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myDelete(Commodity t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int myCount(Commodity t);
}
