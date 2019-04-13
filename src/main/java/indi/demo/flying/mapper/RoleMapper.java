package indi.demo.flying.mapper;

import java.util.Collection;
import java.util.Map;

import indi.demo.flying.pojo.Role;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Role.class })
public interface RoleMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role selectForAssociation(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role mySelect(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Role> mySelectAll(Role t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role mySelectOne(Role t);

	public void myInsert(Role t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdate(Role t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdatePersistent(Role t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myDelete(Role t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int myCount(Role t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updateDirect(Map<String, Object> t);

	public int updateDirectWithoutCache(Map<String, Object> t);
}
