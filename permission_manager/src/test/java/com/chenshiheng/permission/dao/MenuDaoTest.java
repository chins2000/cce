package com.chenshiheng.permission.dao;

import static org.junit.Assert.fail;

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
import com.chenshiheng.permission.entity.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MenuDaoTest {
	
	@Resource
	private MenuDao menuDao;
	
	@Test
	public void testAdd() {
		menuDao.add(new Menu(1,"asd","erg",0,1,null));
	}

	@Test
	public void testAddMore() {
		List<Menu> list = new LinkedList<Menu>();
		list.add(new Menu(1,"asd","erg",0,1,null));
		list.add(new Menu(2,"asgfhjd","rth",0,1,null));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("id", new Object());
		menuDao.addMore(map);
	}

	@Test
	public void testQueryListByPage() {
		Menu menu = new Menu(1,"asd","erg",0,1,null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menu", menu);
		map.put("page", new Page());
		System.out.println(menuDao.queryListByPage(map));
	}

	@Test
	public void testQueryListByPid() {
		System.out.println(menuDao.queryListByPid(0));
	}

	@Test
	public void testQueryListByPerId() {
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		System.out.println(menuDao.queryListByPerId(list));
	}

	@Test
	public void testGetList() {
		menuDao.getList();
	}

	@Test
	public void testFindById() {
		menuDao.findById(1);
	}

	@Test
	public void testUpdate() {
		menuDao.update(new Menu(1,"jytj","ncdhj",0,1,null));
	}

	@Test
	public void testDelete() {
		menuDao.delete(1);
	}

	@Test
	public void testDeleteMore() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(2);
		ids.add(3);
		menuDao.deleteMore(ids);
	}

}
