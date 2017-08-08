package com.chenshiheng.permission.dao;

import java.util.List;

import com.chenshiheng.permission.entity.RoleAndPermission;

public interface RoleAndPermissionDao {
	public int add(RoleAndPermission roleAndPermission);
	
	public int addMore(List<RoleAndPermission> list);
	
	public int update(RoleAndPermission roleAndPermission);
	
	public int delete(RoleAndPermission roleAndPermission);
	
	public int deleteMore(List<RoleAndPermission> list);
}
