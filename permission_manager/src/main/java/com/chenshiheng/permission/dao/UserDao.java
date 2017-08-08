package com.chenshiheng.permission.dao;

import java.util.List;
import java.util.Map;

import com.chenshiheng.permission.dto.Subject;
import com.chenshiheng.permission.entity.User;

public interface UserDao {
	
	public int add(User user);
	
	public int addMore(Map<String,Object> map);
	
	public User login(User user);
	
	public List<User> queryListByPage(Map<String, Object> map);
	
	public List<User> getList();
	
	public User findById(int id);
	
	public int update(User user);
	
	public int delete(int id);
	
	public int deleteMore(List<Integer> list);
	
}
