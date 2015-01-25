package com.stono.ssh.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.stono.ssh.entities.Employee;
import com.stono.ssh.service.DepartmentService;
import com.stono.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,
		ModelDriven<Employee>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
	private Map<String, Object> request;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String delete() throws Exception {
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-success";
	}

	public String list() {
		request.put("employees", employeeService.getAll());
		return "list";
	}

	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public String input() {
		request.put("departments", departmentService.getAll());
		return INPUT;
	}

	public void prepareInput(){
		if(id!=null){
			model = employeeService.get(id);
		}
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	private String lastName;

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String validateLastName() {
		try {
			if (employeeService.lastNameIsValid(lastName)) {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "ajax-success";
	}

	public String save() {
		if(id ==null){
			model.setCreateTime(new Date());
		}else{
			
		}
		employeeService.saveOrUpdate(model);
		return SUCCESS;
	}

	public void prepareSave() {
		if(id == null){
			model = new Employee();
		}else{
			model = employeeService.get(id);
		}
	}

	@Override
	public void prepare() throws Exception {

	}

	private Employee model;

	@Override
	public Employee getModel() {
		return model;
	}
}
