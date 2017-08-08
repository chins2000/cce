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

import com.chenshiheng.permission.dto.Page;
import com.chenshiheng.permission.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

	@Resource
	private UserDao userDao;
	
	@Test
	public void testAddAndUpdateAndDelete() {
		User user = new User(1,"34nrrt","group");
		System.out.println(userDao.add(user));
		user.setPassword("tjjsrt");
		System.out.println(userDao.update(user));
		System.out.println(userDao.delete(user.getId()));
	}

	@Test
	public void testAddMoreAndDeleteMore() {
		List<User> list = new LinkedList<User>();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Integer> idList = new LinkedList<Integer>();
		list.add(new User(1,"6ynys","group"));
		list.add(new User(1,"tsrrrr","group"));
		list.add(new User(1,"tjsgg","group"));
		map.put("list", list);
		map.put("id", new Object());
		System.out.println(userDao.addMore(map));
		System.out.println(map.get("id"));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id")))));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id"))+1)));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id"))+2)));
		System.out.println(userDao.deleteMore(idList));
	}
	
	@Test
	public void testLogin() {
		System.out.println(userDao.login(new User(1,"testname","testpassword")));
	}
	
	@Test
	public void testQueryListByPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", new User(1,"testname","testpassword"));
		map.put("page", new Page());
		System.out.println(userDao.queryListByPage(map));
		System.out.println(((Page)map.get("page")).getTotalNumber()+"");
	}
	
	@Test
	public void testGetList() {
		System.out.println(userDao.getList());
	}

	@Test
	public void testFindById() {
		System.out.println(userDao.findById(1));
	}

}
