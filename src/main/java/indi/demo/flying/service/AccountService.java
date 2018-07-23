package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.AccountMapper;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojoHelper.ServiceSupport;

@Service
public class AccountService extends ServiceSupport<Account_> implements AccountMapper {

	@Autowired
	private AccountMapper mapper;

	@Override
	public Account_ select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public Account_ selectOne(Account_ t) {
		return supportSelectOne(mapper, t);
	}

	@Override
	public void insert(Account_ t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(Account_ t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<Account_> selectAll(Account_ t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(Account_ t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public int delete(Account_ t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(Account_ t) {
		return supportCount(mapper, t);
	}
}
