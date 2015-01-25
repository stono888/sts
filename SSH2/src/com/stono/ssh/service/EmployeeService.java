package com.stono.ssh.service;

import java.util.List;

import com.stono.ssh.dao.EmployeeDao;
import com.stono.ssh.entities.Employee;

public class EmployeeService {

	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void delete(Integer id) {
		employeeDao.delete(id);
	}

	public List<Employee> getAll() {
		List<Employee> list = employeeDao.getAll();
		return list;
	}

	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOrUpdate(employee);
	}

	public boolean lastNameIsValid(String lastName) {
		return employeeDao.getEmployeeByLastName(lastName) == null;
	}

	public Employee get(Integer id) {
		return employeeDao.get(id);
	}
}
