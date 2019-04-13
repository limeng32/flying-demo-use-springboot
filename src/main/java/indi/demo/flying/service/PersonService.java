package indi.demo.flying.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.PersonMapper;
import indi.demo.flying.pojo.Person;
import indi.demo.flying.pojo.Role;

@Service
public class PersonService implements PersonMapper {

	@Autowired
	private PersonMapper mapper;

	@Override
	public Person selectForAssociation(Object id) {
		return mapper.selectForAssociation(id);
	}

	@Override
	public Person mySelect(Object id) {
		return mapper.mySelect(id);
	}

	@Override
	public Collection<Person> mySelectAll(Person t) {
		return mapper.mySelectAll(t);
	}

	@Override
	public Person mySelectOne(Person t) {
		return mapper.mySelectOne(t);
	}

	@Override
	public void myInsert(Person t) {
		mapper.myInsert(t);
	}

	@Override
	public int myUpdate(Person t) {
		return mapper.myUpdate(t);
	}

	@Override
	public int myUpdatePersistent(Person t) {
		return mapper.myUpdatePersistent(t);
	}

	@Override
	public int myDelete(Person t) {
		return mapper.myDelete(t);
	}

	@Override
	public int myCount(Person t) {
		return mapper.myCount(t);
	}

	@Override
	public void loadRole(Role role, Person person) {
		role.removeAllPerson();
		person.setRole(role);
		role.setPerson(mapper.mySelectAll(person));
	}

}
