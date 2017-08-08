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
import com.chenshiheng.permission.entity.Role;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RoleDaoTest {

	@Resource
	private RoleDao roleDao;
	
	@Test
	public void testAdd() {
		Role role = new Role(1,"mfmr","group");
		System.out.println(roleDao.add(role));
		role.setName("yrbm");
		System.out.println(roleDao.update(role));
		System.out.println(roleDao.delete(role.getId()));
	}

	@Test
	public void testAddMore() {
		List<Role> list = new LinkedList<Role>();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Integer> idList = new LinkedList<Integer>();
		list.add(new Role(1,"yuio","group"));
		list.add(new Role(1,"ghtyj","group"));
		list.add(new Role(1,"qmtr","group"));
		map.put("list", list);
		map.put("id", new Object());
		System.out.println(roleDao.addMore(map));
		System.out.println(map.get("id"));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id")))));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id"))+1)));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id"))+2)));
		System.out.println(roleDao.deleteMore(idList));
	}

	@Test
	public void testQueryListByPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", new Role(1,"asd","sdf"));
		map.put("page", new Page());
		System.out.println(roleDao.queryListByPage(map));
		System.out.println(((Page)map.get("page")).getTotalNumber()+"");
	}
	
	@Test
	public void testGetList() {
		System.out.println(roleDao.getList());
	}

	@Test
	public void testFindById() {
		System.out.println(roleDao.findById(1));
	}

}
