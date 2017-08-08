package com.chenshiheng.permission.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenshiheng.permission.dao.PermissionDao;
import com.chenshiheng.permission.entity.Permission;
import com.chenshiheng.permission.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDao permissionDao;
	
	@Override
	public int add(Permission permission) {
		// TODO Auto-generated method stub
		return permissionDao.add(permission);
	}
	
	@Override
	public int addMore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return permissionDao.addMore(map);
	}
	
	@Override
	public List<Permission> queryListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return permissionDao.queryListByPage(map);
	}

	@Override
	public List<Permission> getList() {
		// TODO Auto-generated method stub
		return permissionDao.getList();
	}

	@Override
	public Permission findById(int id) {
		// TODO Auto-generated method stub
		return permissionDao.findById(id);
	}

	@Override
	public int update(Permission permission) {
		// TODO Auto-generated method stub
		return permissionDao.update(permission);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return permissionDao.delete(id);
	}

	@Override
	public int deleteMore(List<Integer> list) {
		// TODO Auto-generated method stub
		return permissionDao.deleteMore(list);
	}

}
