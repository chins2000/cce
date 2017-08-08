package com.chenshiheng.permission.dao;

import java.util.List;
import java.util.Map;

import com.chenshiheng.permission.entity.Permission;

public interface PermissionDao {
	
	public int add(Permission permission);
	
	public int addMore(Map<String,Object> map);
	
	public List<Permission> queryListByPage(Map<String, Object> map);
	
	public List<Permission> getList();
	
	public Permission findById(int id);
	
	public int update(Permission permission);
	
	public int delete(int id);
	
	public int deleteMore(List<Integer> list);
	
}
