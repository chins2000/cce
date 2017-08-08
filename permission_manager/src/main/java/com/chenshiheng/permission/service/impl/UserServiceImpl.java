package com.chenshiheng.permission.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenshiheng.permission.dao.UserAndRoleDao;
import com.chenshiheng.permission.dao.UserDao;
import com.chenshiheng.permission.dto.Subject;
import com.chenshiheng.permission.dto.UserPermission;
import com.chenshiheng.permission.entity.Role;
import com.chenshiheng.permission.entity.RoleAndPermission;
import com.chenshiheng.permission.entity.User;
import com.chenshiheng.permission.entity.UserAndRole;
import com.chenshiheng.permission.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private UserAndRoleDao userAndRoleDao;
	
	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		int result = userDao.add(user);
		List<UserAndRole> list = new LinkedList<UserAndRole>();
		if(user.getRoles()==null || user.getRoles().isEmpty()){
			return result;
		}
		for(Role role : user.getRoles()){
			list.add(new UserAndRole(user.getId(),role.getId()));
		}
		if(!list.isEmpty()){
			userAndRoleDao.addMore(list);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int addMore(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = userDao.addMore(map);
		List<UserAndRole> list = new LinkedList<UserAndRole>();
		List<User> users = (List<User>)map.get("list");
		for(User user : users){
			for(Role role : user.getRoles()){
				list.add(new UserAndRole(users.indexOf(user)+Integer.parseInt(String.valueOf((long)map.get("id"))),role.getId()));
			}
		}
		if(!list.isEmpty()){
			userAndRoleDao.addMore(list);
		}
		return result;
	}
	
	@Override
	public List<User> queryListByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userDao.queryListByPage(map);
	}

	@Override
	public List<User> getList() {
		// TODO Auto-generated method stub
		return userDao.getList();
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		List<UserAndRole> addlist = new LinkedList<UserAndRole>();
		List<UserAndRole> deletelist = new LinkedList<UserAndRole>();
		User modeluser = userDao.findById(user.getId());
		if(modeluser.getRoles()==null){
			modeluser.setRoles(new ArrayList<Role>());
		}
		if(user.getRoles()==null){
			user.setRoles(new ArrayList<Role>());
		}
		for(Role role : user.getRoles()){
			if(!modeluser.getRoles().contains(role))
				addlist.add(new UserAndRole(user.getId(),role.getId()));
		}
		for(Role role : modeluser.getRoles()){
			if(!user.getRoles().contains(role))
				deletelist.add(new UserAndRole(user.getId(),role.getId()));
		}
		if(!deletelist.isEmpty()){
			userAndRoleDao.deleteMore(deletelist);
		}
		if(!addlist.isEmpty()){
			userAndRoleDao.addMore(addlist);
		}
		return userDao.update(user);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return userDao.delete(id);
	}

	@Override
	public int deleteMore(List<Integer> list) {
		// TODO Auto-generated method stub
		return userDao.deleteMore(list);
	}

	@Override
	public Subject login(User user) {
		// TODO Auto-generated method stub
		user=userDao.login(user);
		if(user==null){
			return null;
		}
		Subject subject = new Subject(user.getId(),user.getUsername(),user.getPassword(),new LinkedList<UserPermission>());
		List<UserPermission> list = subject.getUserPermissions();
		UserPermission userPermission = new UserPermission();
		for(Role role : user.getRoles()){
			for(RoleAndPermission roleAndPermission : role.getRoleAndPermissions()){
				userPermission = new UserPermission(roleAndPermission.getRoleId(),roleAndPermission.getPermission().getId(),roleAndPermission.getPermission().getName(),roleAndPermission.getPermission().getGroup(),roleAndPermission.isAdd(),roleAndPermission.isVisit(),roleAndPermission.isDelete(),roleAndPermission.isUpdate());
				int index = list.indexOf(userPermission);
				if(index==-1){
					list.add(userPermission);
				}else{
					UserPermission inuserPermission = list.get(index);
					if(userPermission.isAdd()){
						inuserPermission.setAdd(true);
					}
					if(userPermission.isVisit()){
						inuserPermission.setVisit(true);
					}
					if(userPermission.isUpdate()){
						inuserPermission.setUpdate(true);
					}
					if(userPermission.isDelete()){
						inuserPermission.setDelete(true);
					}
				}
			}
		}
		return subject;
	}

}
