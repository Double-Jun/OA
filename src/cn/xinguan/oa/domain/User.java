package cn.xinguan.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * 
 * @author MingJun Chen
 * 
 */
public class User {

	private Long id; // id
	private String loginName; // 登陆名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明

	// 与部门和角色之间的关系表示
	private Department department;
	private Set<Role> roles = new HashSet<Role>();

	/**
	 * 根据制定权限名判断用户是否拥有该权限
	 * 
	 * @return
	 */
	public boolean hasPrivilegeByName(String name) {
		// 超级管理员
		if (isAdmin()) {
			System.out.println("User is Admin");
			return true;
		}
		// 普通用户
		for (Role role : roles) {
			for (Privilege priv : role.getPrivileges()) {
				if (priv.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isAdmin() {
		return "admin".equals(loginName);
	}

	// ---------set和get方法-----------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	// -------------构造方法-----------
	public User() {

	}

	public User(String loginName, String password, String name, String gender, String phoneNumber, String email, String description, Department department, Set<Role> roles) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.description = description;
		this.department = department;
		this.roles = roles;
	}

	// =======================
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", description=" + description + ", department=" + department + ", roles=" + roles + "]";
	}

}
