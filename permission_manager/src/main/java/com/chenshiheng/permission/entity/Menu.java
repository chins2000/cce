package com.chenshiheng.permission.entity;

import java.util.List;

public class Menu {
	private int id;
	private String url;
	private String name;
	private int pid;
	private int permissionId;
	private List<Menu> subMenus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", url=" + url + ", name=" + name + ", pid=" + pid + ", permissionId=" + permissionId
				+ ", subMenus=" + subMenus + "]";
	}
	public Menu() {
	}
	public Menu(int id, String url, String name, int pid, int permissionId, List<Menu> subMenus) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.pid = pid;
		this.permissionId = permissionId;
		this.subMenus = subMenus;
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
		Menu other = (Menu) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
