package com.chenshiheng.permission.dto;

public class UserPermission {
	private int roleId;
	private int permissionId;
	private String name;
	private String group;
	private boolean add;
	private boolean visit;
	private boolean delete;
	private boolean update;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
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
	public boolean isAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public boolean isVisit() {
		return visit;
	}
	public void setVisit(boolean visit) {
		this.visit = visit;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	
	
	public UserPermission() {
	}
	public UserPermission(int roleId, int permissionId, String name, String group, boolean add, boolean visit,
			boolean delete, boolean update) {
		this.roleId = roleId;
		this.permissionId = permissionId;
		this.name = name;
		this.group = group;
		this.add = add;
		this.visit = visit;
		this.delete = delete;
		this.update = update;
	}
	@Override
	public String toString() {
		return "UserPermission [roleId=" + roleId + ", permissionId=" + permissionId + ", name=" + name + ", group="
				+ group + ", add=" + add + ", visit=" + visit + ", delete=" + delete + ", update=" + update + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + permissionId;
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
		UserPermission other = (UserPermission) obj;
		if (permissionId != other.permissionId)
			return false;
		return true;
	}
	
}
