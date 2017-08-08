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
import com.chenshiheng.permission.entity.Permission;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class PermissionDaoTest {
	
	@Resource
	private PermissionDao permissionDao;
	
	@Test
	public void testAddAndUpdateAndDelete() {
		Permission permission = new Permission(1,"hgyu","group");
		System.out.println(permissionDao.add(permission));
		permission.setName("yetffbd");
		System.out.println(permissionDao.update(permission));
		System.out.println(permissionDao.delete(permission.getId()));
	}

	@Test
	public void testAddMoreAndDeleteMore() {
		List<Permission> list = new LinkedList<Permission>();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Integer> idList = new LinkedList<Integer>();
		list.add(new Permission(1,"erer","group"));
		list.add(new Permission(1,"fdghdfh","group"));
		list.add(new Permission(1,"ytjtry","group"));
		map.put("list", list);
		map.put("id", new Object());
		System.out.println(permissionDao.addMore(map));
		System.out.println(map.get("id"));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id")))));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id"))+1)));
		idList.add(Integer.parseInt(String.valueOf(((long)map.get("id"))+2)));
		System.out.println(permissionDao.deleteMore(idList));
	}
	
	@Test
	public void testQueryListByPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("permission", new Permission(1,"fghn","ytrth"));
		map.put("page", new Page());
		System.out.println(permissionDao.queryListByPage(map));
		System.out.println(((Page)map.get("page")).getTotalNumber()+"");
	}

	@Test
	public void testGetList() {
		System.out.println(permissionDao.getList());
	}

	@Test
	public void testFindById() {
		System.out.println(permissionDao.findById(1));
	}

}
