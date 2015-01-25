package com.stono.ssh.dao;

import java.util.List;

import com.stono.ssh.entities.Department;

public class DepartmentDao extends BaseDao {

	public List<Department> getAll() {
		String hql = "from Department";
		return getSession().createQuery(hql).list();
	}
}
