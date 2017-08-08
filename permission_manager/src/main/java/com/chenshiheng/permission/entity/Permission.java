package com.chenshiheng.permission.entity;


public class Permission {
	private int id;
	private String name;
	private String group;
	
	public Permission() {
	}
	public Permission(int id) {
		this.id = id;
	}
	public Permission(int id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
	}
	public Permission(String name, String group) {
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
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", group=" + group + "]";
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
		Permission other = (Permission) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
