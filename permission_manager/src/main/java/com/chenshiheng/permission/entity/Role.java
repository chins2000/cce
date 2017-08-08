package com.chenshiheng.permission.entity;

import java.util.List;

public class Role {
	private int id;
	private String name;
	private String group;
	private List<RoleAndPermission> roleAndPermissions;
	public Role() {}
	public Role(int id) {
		this.id = id;
	}
	public Role(String name, String group) {
		this.name = name;
		this.group = group;
	}
	public Role(int id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public List<RoleAndPermission> getRoleAndPermissions() {
		return roleAndPermissions;
	}
	public void setRoleAndPermissions(List<RoleAndPermission> roleAndPermissions) {
		this.roleAndPermissions = roleAndPermissions;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", group=" + group + ", roleAndPermissions=" + roleAndPermissions
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
