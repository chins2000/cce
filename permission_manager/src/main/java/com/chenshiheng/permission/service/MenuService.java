package com.chenshiheng.permission.service;

import java.util.List;
import java.util.Map;

import com.chenshiheng.permission.entity.Menu;

public interface MenuService {
	public int add(Menu menu);
	
	public int addMore(Map<String, Object> map);
	
	public List<Menu> queryListByPage(Map<String, Object> map);
	
	public List<Menu> queryListByPid(int pid);
	
	public List<Menu> queryListByPerId(List<Integer> PerIds);
	
	public String getzTree();
	
	public List<Menu> getList();
	
	public Menu findById(int id);
	
	public int update(Menu menu);
	
	public int delete(int id);
	
	public int deleteMore(List<Integer> ids);

}
