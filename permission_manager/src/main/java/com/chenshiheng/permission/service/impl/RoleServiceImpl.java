package com.chenshiheng.permission.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenshiheng.permission.dao.RoleAndPermissionDao;
import com.chenshiheng.permission.dao.RoleDao;
import com.chenshiheng.permission.entity.Role;
import com.chenshiheng.permission.entity.RoleAndPermission;
import com.chenshiheng.permission.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	@Resource
	private RoleAndPermissionDao roleAndPermissionDao;

	@Override
	public int add(Role role) {
		// TODO Auto-generated method stub
		int result = roleDao.add(role);
		if (role.getRoleAndPermissions() == null || role.getRoleAndPermissions().isEmpty()) {
			return result;
		}
		if (role.getRoleAndPermissions().size() > 0) {
			for(RoleAndPermission roleAndPermission : role.getRoleAndPermissions()){
				roleAndPermission.setRoleId(role.getId());
			}
			roleAndPermissionDao.addMore(role.getRoleAndPermissions());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int addMore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = roleDao.addMore(map);
		List<RoleAndPermission> list = new LinkedList<RoleAndPermission>();
		List<Role> roles = ((List<Role>) map.get("list"));
		for (int i = 0; i < roles.size(); i++){
			for(RoleAndPermission roleAndPermission : roles.get(i).getRoleAndPermissions()){
				roleAndPermission.setRoleId(Integer.parseInt(String.valueOf((long)map.get("id")))+i);
			}
			list.addAll(roles.get(i).getRoleAndPermissions());
		}
		if (!list.isEmpty()) {
			roleAndPermissionDao.addMore(list);
		}
		return result;
	}

	@Override
	public List<Role> queryListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleDao.queryListByPage(map);
	}

	@Override
	public List<Role> getList() {
		// TODO Auto-generated method stub
		return roleDao.getList();
	}

	@Override
	public Role findById(int id) {
		// TODO Auto-generated method stub
		return roleDao.findById(id);
	}

	@Override
	public int update(Role role) {
		// TODO Auto-generated method stub
		List<RoleAndPermission> addlist = new LinkedList<RoleAndPermission>();
		List<RoleAndPermission> deletelist = new LinkedList<RoleAndPermission>();
		List<RoleAndPermission> updatelist = new LinkedList<RoleAndPermission>();
		Role modelRole = roleDao.findById(role.getId());
		if (modelRole.getRoleAndPermissions() == null) {
			modelRole.setRoleAndPermissions(new ArrayList<RoleAndPermission>());
		}
		if (role.getRoleAndPermissions() == null) {
			role.setRoleAndPermissions(new ArrayList<RoleAndPermission>());
		}
		for (RoleAndPermission roleAndPermission : role.getRoleAndPermissions()) {
			if (!modelRole.getRoleAndPermissions().contains(roleAndPermission))
				addlist.add(roleAndPermission);
			else
				updatelist.add(roleAndPermission);
		}
		for (RoleAndPermission roleAndPermission : modelRole.getRoleAndPermissions()) {
			if (!role.getRoleAndPermissions().contains(roleAndPermission))
				deletelist.add(roleAndPermission);
		}
		for (RoleAndPermission roleAndPermission : updatelist) {
			roleAndPermissionDao.update(roleAndPermission);
		}
		if (!deletelist.isEmpty()) {
			roleAndPermissionDao.deleteMore(deletelist);
		}
		if (!addlist.isEmpty()) {
			roleAndPermissionDao.addMore(addlist);
		}
		return roleDao.update(role);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return roleDao.delete(id);
	}

	@Override
	public int deleteMore(List<Integer> list) {
		// TODO Auto-generated method stub
		return roleDao.deleteMore(list);
	}

}
