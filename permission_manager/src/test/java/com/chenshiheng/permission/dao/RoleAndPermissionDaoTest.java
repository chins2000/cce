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
import com.chenshiheng.permission.entity.RoleAndPermission;
import com.chenshiheng.permission.entity.User;
import com.chenshiheng.permission.entity.UserAndRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RoleAndPermissionDaoTest {

	@Resource
	private RoleAndPermissionDao roleAndPermissionDao;

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	@Test
	public void testAddAndDelete() {
		Role role = new Role(1,"qweqe","ergreg");
		Permission permission = new Permission(1,"name","group");
		permissionDao.add(permission);
		roleDao.add(role);
		RoleAndPermission roleAndPermission = new RoleAndPermission(role.getId(),permission,true,true,true,true);
		System.out.println(roleAndPermission);
		System.out.println(roleAndPermissionDao.add(roleAndPermission));
		System.out.println(roleAndPermissionDao.delete(roleAndPermission));
		permissionDao.delete(permission.getId());
		roleDao.delete(role.getId());
	}

	@Test
	public void testAddMoreAndDeleteMore() {
		List<Permission> permissionList = new LinkedList<Permission>();
		List<Role> roleList = new LinkedList<Role>();
		List<RoleAndPermission> list = new LinkedList<RoleAndPermission>();
		Map<String,Object> permissionMap = new HashMap<String,Object>();
		Map<String,Object> roleMap = new HashMap<String,Object>();
		List<Integer> permissionIdList = new LinkedList<Integer>();
		List<Integer> roleIdList = new LinkedList<Integer>();
		permissionList.add(new Permission(77,"nafme","group"));
		permissionList.add(new Permission(88,"erg","group"));
		permissionList.add(new Permission(99,"nadfbdme","group"));
		roleList.add(new Role(66,"erte","rtyu"));
		roleList.add(new Role(77,"vbm","fghj"));
		roleList.add(new Role(88,"herwy","dghj"));
		permissionMap.put("list", permissionList);
		permissionMap.put("id", new Object());
		roleMap.put("list", roleList);
		roleMap.put("id", new Object());
		permissionDao.addMore(permissionMap);
		roleDao.addMore(roleMap); 
		for(Permission permission : permissionList){
			for(Role role : roleList){
				list.add(new RoleAndPermission(role.getId(),permission,true,true,true,true));
			}
		}
		System.out.println(roleAndPermissionDao.addMore(list));
		System.out.println(roleAndPermissionDao.deleteMore(list));
		for (int i = 0; i < roleList.size(); i++) {
			roleIdList.add(Integer.parseInt(String.valueOf(((long)roleMap.get("id"))+i)));
		}
		for (int i = 0; i < permissionList.size(); i++) {
			permissionIdList.add(Integer.parseInt(String.valueOf(((long)permissionMap.get("id"))+i)));
		}
		permissionDao.deleteMore(permissionIdList);
		roleDao.deleteMore(roleIdList);
	}

}
