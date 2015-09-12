package cn.xinguan.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门实体类
 * 
 * @author MingJun Chen
 * 
 */
public class Department {
	private Long id;
	private String name;
	private String description;

	/** 与用户和其他部门之间的关系表示 */
	private Set<User> users = new HashSet<User>(); // 关联用户
	private Department parent; // 关联上级部门
	private Set<Department> children = new HashSet<Department>(); // 关联下级部门

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}

	// ================================================

}
