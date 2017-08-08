package com.chenshiheng.permission.dao;

import java.util.List;
import java.util.Map;

import com.chenshiheng.permission.entity.Role;


public interface RoleDao {
	
	public int add(Role role);
	
	public int addMore(Map<String,Object> map);
	
	public List<Role> queryListByPage(Map<String, Object> map);
	
	public List<Role> getList();
	
	public Role findById(int id);
	
	public int update(Role role);
	
	public int delete(int id);
	
	public int deleteMore(List<Integer> list);
	
}
