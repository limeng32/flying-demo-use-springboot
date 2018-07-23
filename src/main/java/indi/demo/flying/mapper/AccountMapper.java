package indi.demo.flying.mapper;

import java.util.Collection;

import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojoHelper.MapperFace;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Account_.class })
public interface AccountMapper extends MapperFace<Account_> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account_> selectAll(Account_ t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ selectOne(Account_ t);

	@Override
	public void insert(Account_ t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Account_ t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Account_ t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Account_ t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Account_ t);

}
