package com.chenshiheng.permission.dao;

import java.util.List;

import com.chenshiheng.permission.entity.UserAndRole;

public interface UserAndRoleDao {
	public int add(UserAndRole userAndRole);
	
	public int addMore(List<UserAndRole> list);
	
	public int delete(UserAndRole userAndRole);
	
	public int deleteMore(List<UserAndRole> list);
}
