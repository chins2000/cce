package com.chenshiheng.permission.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenshiheng.permission.entity.Permission;
import com.chenshiheng.permission.entity.Role;
import com.chenshiheng.permission.entity.User;
import com.chenshiheng.permission.entity.UserAndRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserAndRoleDaoTest {

	@Resource
	private UserAndRoleDao userAndRoleDao;

	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;
	@Test
	public void testAddAndDelete() {
		Role role = new Role(1,"qweqe","ergreg");
		User user = new User(1,"name","group");
		userDao.add(user);
		roleDao.add(role);
		UserAndRole userAndRole = new UserAndRole(user.getId(),role.getId());
		System.out.println(userAndRoleDao.add(userAndRole));
		System.out.println(userAndRoleDao.delete(userAndRole));
		userDao.delete(user.getId());
		roleDao.delete(role.getId());
	}

	@Test
	public void testAddMoreAndDeleteMore() {
		List<User> userList = new LinkedList<User>();
		List<Role> roleList = new LinkedList<Role>();
		List<UserAndRole> list = new LinkedList<UserAndRole>();
		Map<String,Object> userMap = new HashMap<String,Object>();
		Map<String,Object> roleMap = new HashMap<String,Object>();
		List<Integer> userIdList = new LinkedList<Integer>();
		List<Integer> roleIdList = new LinkedList<Integer>();
		userList.add(new User(22,"asddf","fghgfh"));
		userList.add(new User(32,"gfj","yuity"));
		userList.add(new User(42,"ghk","fghgyuiytfh"));
		roleList.add(new Role(22,"erte","rtyu"));
		roleList.add(new Role(32,"vbm","fghj"));
		roleList.add(new Role(42,"herwy","dghj"));
		userMap.put("list", userList);
		userMap.put("id", new Object());
		roleMap.put("list", roleList);
		roleMap.put("id", new Object());
		userDao.addMore(userMap);
		roleDao.addMore(roleMap);
		for(User user : userList){
			for(Role role : roleList){
				list.add(new UserAndRole(user.getId(),role.getId()));
			}
		}
		System.out.println(userAndRoleDao.addMore(list));
		System.out.println(userAndRoleDao.deleteMore(list));
		for(int i = 0;i<userList.size();i++){
			userIdList.add(Integer.parseInt(String.valueOf(((long)userMap.get("id"))+i)));
		}
		for(int i = 0;i<roleList.size();i++){
			roleIdList.add(Integer.parseInt(String.valueOf(((long)roleMap.get("id"))+i)));
		}
		userDao.deleteMore(userIdList);
		roleDao.deleteMore(roleIdList);
	}

}
