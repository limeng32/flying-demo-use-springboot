package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table(name = "ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * role name
	 * 
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * role value
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "VALUE", jdbcType = JdbcType.VARCHAR)
	private RoleEnum value;

	private java.util.Collection<Person> person;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleEnum getValue() {
		return value;
	}

	public void setValue(RoleEnum value) {
		this.value = value;
	}

	public java.util.Collection<Person> getPerson() {
		if (person == null) {
			person = new java.util.LinkedHashSet<Person>();
		}
		return person;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<Person> getIteratorPerson() {
		if (person == null) {
			person = new java.util.LinkedHashSet<Person>();
		}
		return person.iterator();
	}

	public void setPerson(java.util.Collection<Person> newPerson) {
		removeAllPerson();
		for (java.util.Iterator<Person> iter = newPerson.iterator(); iter.hasNext();) {
			addPerson((Person) iter.next());
		}
	}

	public void addPerson(Person newPerson) {
		if (newPerson == null) {
			return;
		}
		if (this.person == null) {
			this.person = new java.util.LinkedHashSet<Person>();
		}
		if (!this.person.contains(newPerson)) {
			this.person.add(newPerson);
			newPerson.setRole(this);
		} else {
			for (Person temp : this.person) {
				if (newPerson.equals(temp)) {
					if (temp != newPerson) {
						removePerson(temp);
						this.person.add(newPerson);
						newPerson.setRole(this);
					}
					break;
				}
			}
		}
	}

	public void removePerson(Person oldPerson) {
		if (oldPerson == null) {
			return;
		}
		if (this.person != null) {
			if (this.person.contains(oldPerson)) {
				for (Person temp : this.person) {
					if (oldPerson.equals(temp)) {
						if (temp != oldPerson) {
							temp.setRole(null);
						}
						break;
					}
				}
				this.person.remove(oldPerson);
				oldPerson.setRole(null);
			}
		}
	}

	public void removeAllPerson() {
		if (person != null) {
			Person oldPerson;
			for (java.util.Iterator<Person> iter = getIteratorPerson(); iter.hasNext();) {
				oldPerson = (Person) iter.next();
				iter.remove();
				oldPerson.setRole(null);
			}
			person.clear();
		}
	}
}
