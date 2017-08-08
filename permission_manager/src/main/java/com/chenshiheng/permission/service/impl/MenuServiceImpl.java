package com.chenshiheng.permission.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenshiheng.permission.dao.MenuDao;
import com.chenshiheng.permission.entity.Menu;
import com.chenshiheng.permission.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	@Override
	public int add(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.add(menu);
	}

	@Override
	public int addMore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuDao.addMore(map);
	}

	@Override
	public List<Menu> queryListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuDao.queryListByPage(map);
	}

	@Override
	public List<Menu> queryListByPid(int pid) {
		// TODO Auto-generated method stub
		return menuDao.queryListByPid(pid);
	}

	@Override
	public List<Menu> queryListByPerId(List<Integer> PerIds) {
		// TODO Auto-generated method stub
		return menuDao.queryListByPerId(PerIds);
	}

	@Override
	public List<Menu> getList() {
		// TODO Auto-generated method stub
		return menuDao.getList();
	}
	
	@Override
	public String getzTree() {
		String zTree = null; 
		return zTree;
	}

	@Override
	public Menu findById(int id) {
		// TODO Auto-generated method stub
		return menuDao.findById(id);
	}

	@Override
	public int update(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.update(menu);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return menuDao.delete(id);
	}

	@Override
	public int deleteMore(List<Integer> ids) {
		// TODO Auto-generated method stub
		return menuDao.deleteMore(ids);
	}

}
