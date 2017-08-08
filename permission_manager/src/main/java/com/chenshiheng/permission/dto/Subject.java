package com.chenshiheng.permission.dto;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.DigestUtils;

import com.chenshiheng.permission.enums.PermissionEnum;

public class Subject {
	private int id;
	private String username;
	private String password;
	private List<UserPermission> userPermissions;

	@SuppressWarnings("unused")
	public String getMD5(String slat) {
		String base = this.id + "/as" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserPermission> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(List<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	public Subject() {
	}

	public Subject(int id, String username, String password, List<UserPermission> userPermissions) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userPermissions = userPermissions;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", username=" + username + ", password=" + password + ", userPermissions="
				+ userPermissions + "]";
	}

	public List<Integer> getPermissionIds() {
		List<Integer> list = new LinkedList<Integer>();
		for (UserPermission userPermission : this.userPermissions) {
			list.add(userPermission.getPermissionId());
		}
		return list;
	}

	public boolean hasPermission(int permissionId, PermissionEnum type) {
		UserPermission userPermission = null;
		for (UserPermission userPermissiontemp : userPermissions) {
			if (permissionId == userPermissiontemp.getPermissionId()) {
				userPermission = userPermissiontemp;
				break;
			}
		}
		if (userPermission != null) {
			if (type.equals(PermissionEnum.ADD)) {
				return userPermission.isAdd();
			}
			if (type.equals(PermissionEnum.DELETE)) {
				return userPermission.isDelete();
			}
			if (type.equals(PermissionEnum.UPDATE)) {
				return userPermission.isUpdate();
			}
			if (type.equals(PermissionEnum.VISIT)) {
				return userPermission.isVisit();
			}
		}
		return false;
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
		Subject other = (Subject) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
