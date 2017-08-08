package com.chenshiheng.permission.entity;

public class RoleAndPermission {
	private int roleId;
	private int permissionId;
	private Permission permission;
	private boolean visit;
	private boolean add;
	private boolean update;
	private boolean delete;
	
	public RoleAndPermission() {
	}
	public RoleAndPermission(int roleId, Permission permission) {
		this.roleId = roleId;
		this.permission = permission;
	}
	public RoleAndPermission(int roleId, Permission permission, boolean visit, boolean add, boolean update,
			boolean delete) {
		this.roleId = roleId;
		this.permission = permission;
		this.visit = visit;
		this.add = add;
		this.update = update;
		this.delete = delete;
	}

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

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public boolean isVisit() {
		return visit;
	}
	public void setVisit(boolean visit) {
		this.visit = visit;
	}
	public boolean isAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "RoleAndPermission [roleId=" + roleId + ", permission=" + permission + ", visit=" + visit + ", add="
				+ add + ", update=" + update + ", delete=" + delete + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + roleId;
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
		RoleAndPermission other = (RoleAndPermission) obj;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}
	
}
