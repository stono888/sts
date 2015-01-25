package com.stono.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stono.ssh.entities.Employee;

public class EmployeeDao extends BaseDao {

	public void delete(Integer id) {
		String hql = "delete from Employee e where e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}

	public List<Employee> getAll() {
		String hql = "from Employee e left outer join fetch e.department";
		return getSession().createQuery(hql).list();
	}

	public void saveOrUpdate(Employee employee) {
		getSession().saveOrUpdate(employee);
	}

	public Employee getEmployeeByLastName(String lastName) {
		String hql = "from Employee e where e.lastName = ?";
		Query query = getSession().createQuery(hql).setString(0, lastName);
		return (Employee) query.uniqueResult();

	}
	
	public Employee get(Integer id){
		return (Employee) getSession().get(Employee.class,id);
	}
}
