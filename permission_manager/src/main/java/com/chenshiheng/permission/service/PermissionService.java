package com.chenshiheng.permission.service;

import java.util.List;
import java.util.Map;

import com.chenshiheng.permission.entity.Permission;

public interface PermissionService {
	public int add(Permission permission);
	
	public int addMore(Map<String,Object> map);
	
	public List<Permission> queryListByPage(Map<String, Object> map);
	
	public List<Permission> getList();
	
	public Permission findById(int id);
	
	public int update(Permission permission);
	
	public int delete(int id);
	
	public int deleteMore(List<Integer> list);
	
}
