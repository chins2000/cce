package com.chenshiheng.permission.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenshiheng.permission.dto.AjaxResult;
import com.chenshiheng.permission.dto.Page;
import com.chenshiheng.permission.dto.Subject;
import com.chenshiheng.permission.entity.Permission;
import com.chenshiheng.permission.entity.Role;
import com.chenshiheng.permission.entity.RoleAndPermission;
import com.chenshiheng.permission.enums.PermissionEnum;
import com.chenshiheng.permission.service.PermissionService;
import com.chenshiheng.permission.service.RoleService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	@Resource(name="permissionServiceImpl")
	private PermissionService permissionService;
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model, HttpSession httpSession,int permissionIdMark){
		Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}
		List<Role> list = new LinkedList<Role>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("role", null);
		map.put("page", page);
		list=roleService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("permissionIdMark", permissionIdMark);
		model.addAttribute("page", page);
		return "role/list";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model, HttpSession httpSession,int permissionIdMark,String search){
		Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}
		List<Role> list = new LinkedList<Role>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("role", new Role(search,search));
		map.put("page", page);
		list=roleService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("permissionIdMark", permissionIdMark);
		model.addAttribute("page", page);
		return "role/list";
	}
	
	@RequestMapping(value="/searchpage",method=RequestMethod.GET)
	public String searchpage(Model model, HttpSession httpSession,int permissionIdMark,String search,Page page){
		Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}
		List<Role> list = new LinkedList<Role>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("role", new Role(search,search));
		page.count();
		map.put("page", page);
		list=roleService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("permissionIdMark", permissionIdMark);
		model.addAttribute("page", page);
		return "role/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult<Object> delete(int id){
		AjaxResult<Object> result = new AjaxResult<Object>();
		if(roleService.delete(id)>0){
			result.setResult("success");
		}else{
			result.setResult("error");
		}
		return result;
	}
	
	@RequestMapping(value="/deleteMore",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult<Object> deleteMore(String ids){
		AjaxResult<Object> result = new AjaxResult<Object>();
		String[] arrids = ids.split(",");
		List<Integer> list = new LinkedList<Integer>();
		for(String id : arrids){
			if(id.equals(""))
				continue;
			list.add(Integer.parseInt(id));
		}
		if(roleService.deleteMore(list)>0){
			result.setResult("success");
		}else{
			result.setResult("error");
		}
		return result;
	}
	
	@RequestMapping(value="/toadd")
	public String toadd(Model model){
		List<Permission> permissionList = permissionService.getList();
		List<String> realGroup = new LinkedList<String>();
		for(Permission permission : permissionList){
			if(!(permission.getGroup()==null || permission.getGroup().equals("")))
				if(!realGroup.contains(permission.getGroup()))
					realGroup.add(permission.getGroup());
		}
		StringBuilder zNodesBuilder = new StringBuilder();
		String zNodes ="[";
		zNodesBuilder.append("[");
		for(String group : realGroup){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:\""+group+"\",");
			zNodesBuilder.append("pId:0,");
			zNodesBuilder.append("name:\""+group+"\",");
			zNodesBuilder.append("nocheck: true");
			zNodesBuilder.append("},");
		}
		for(Permission permission : permissionList){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:"+permission.getId()+",");
			if(permission.getGroup()==null || permission.getGroup().equals("")){
				zNodesBuilder.append("pId:0,");
			}else{
				zNodesBuilder.append("pId:\""+permission.getGroup()+"\",");
			}
			zNodesBuilder.append("name:\""+permission.getName()+"\"");
			zNodesBuilder.append("},");
			//visit
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			zNodesBuilder.append("name:\"visit\"");
			zNodesBuilder.append("},");
			//delete
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			zNodesBuilder.append("name:\"delete\"");
			zNodesBuilder.append("},");
			//add
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			zNodesBuilder.append("name:\"add\"");
			zNodesBuilder.append("},");
			//update
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			zNodesBuilder.append("name:\"update\"");
			zNodesBuilder.append("},");
		}
		if(zNodesBuilder.length()>1)
			zNodes=zNodesBuilder.substring(0, zNodesBuilder.length()-1);
		zNodes=zNodes+"]";
		model.addAttribute("zNodes", zNodes);
		return "role/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,Role role,String permissionIds){
		JSONArray permissionArr = JSONArray.fromObject(permissionIds);
		List<RoleAndPermission> roleAndPermissions = new LinkedList<RoleAndPermission>();
		//{id:  ,visit:   ,add:   ,update:   ,delete:   }
		for (int i = 0; i < permissionArr.size(); i++) {
			JSONObject permission = permissionArr.getJSONObject(i);
			roleAndPermissions.add(new RoleAndPermission(role.getId(),new Permission(Integer.parseInt(permission.getString("id")), null, null),permission.getBoolean("visit"),permission.getBoolean("add"),permission.getBoolean("update"),permission.getBoolean("delete")));
		}
		role.setRoleAndPermissions(roleAndPermissions);
		model.addAttribute("msg", roleService.add(role));
		return "../../result";
	}
	
	@RequestMapping(value="/toupdate")
	public String toupdate(Model model,String id){
		List<Permission> permissionList = permissionService.getList();
		List<String> realGroup = new LinkedList<String>();
		Role role = roleService.findById(Integer.parseInt(id));
		String permissionNames = "";
		String permissionIds = "[";
		for(RoleAndPermission roleAndPermission : role.getRoleAndPermissions()){
			permissionNames+=roleAndPermission.getPermission().getName()+",";
			permissionIds+="{\"id\":\""+roleAndPermission.getPermission().getId()+"\",";
			permissionIds+="\"visit\":"+roleAndPermission.isVisit()+",";
			permissionIds+="\"add\":"+roleAndPermission.isAdd()+",";
			permissionIds+="\"update\":"+roleAndPermission.isUpdate()+",";
			permissionIds+="\"delete\":"+roleAndPermission.isDelete()+"},";
		}
		if(permissionNames.length()>0)
			permissionNames=permissionNames.substring(0, permissionNames.length()-1);
		if(permissionIds.length()>1){
			permissionIds=permissionIds.substring(0, permissionIds.length()-1);
			permissionIds+="]";
		}
		for(Permission permission : permissionList){
			if(!(permission.getGroup()==null || permission.getGroup().equals("")))
				if(!realGroup.contains(permission.getGroup()))
					realGroup.add(permission.getGroup());
		}
		StringBuilder zNodesBuilder = new StringBuilder();
		String zNodes ="[";
		zNodesBuilder.append("[");
		for(String group : realGroup){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:\""+group+"\",");
			zNodesBuilder.append("pId:0,");
			zNodesBuilder.append("name:\""+group+"\",");
			zNodesBuilder.append("nocheck: true");
			zNodesBuilder.append("},");
		}
		for(Permission permission : permissionList){
			RoleAndPermission roleAndPermission = null;
			int index = role.getRoleAndPermissions().indexOf(new RoleAndPermission(role.getId(),new Permission(permission.getId())));
			if(index!=-1)
				roleAndPermission=role.getRoleAndPermissions().get(index);
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:"+permission.getId()+",");
			if(permission.getGroup()==null || permission.getGroup().equals("")){
				zNodesBuilder.append("pId:0,");
			}else{
				zNodesBuilder.append("pId:\""+permission.getGroup()+"\",");
			}
			if(roleAndPermission!=null){
				zNodesBuilder.append("checked:true,");
			}
			zNodesBuilder.append("name:\""+permission.getName()+"\"");
			zNodesBuilder.append("},");
			//visit
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			if(roleAndPermission!=null && roleAndPermission.isVisit()){
				zNodesBuilder.append("checked:true,");
			}
			zNodesBuilder.append("name:\"visit\"");
			zNodesBuilder.append("},");
			//delete
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			if(roleAndPermission!=null && roleAndPermission.isDelete()){
				zNodesBuilder.append("checked:true,");
			}
			zNodesBuilder.append("name:\"delete\"");
			zNodesBuilder.append("},");
			//add
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			if(roleAndPermission!=null && roleAndPermission.isAdd()){
				zNodesBuilder.append("checked:true,");
			}
			zNodesBuilder.append("name:\"add\"");
			zNodesBuilder.append("},");
			//update
			zNodesBuilder.append("{");
			zNodesBuilder.append("pId:\""+permission.getId()+"\",");
			if(roleAndPermission!=null && roleAndPermission.isUpdate()){
				zNodesBuilder.append("checked:true,");
			}
			zNodesBuilder.append("name:\"update\"");
			zNodesBuilder.append("},");
		}
		if(zNodesBuilder.length()>1)
			zNodes=zNodesBuilder.substring(0, zNodesBuilder.length()-1);
		zNodes=zNodes+"]";
		model.addAttribute("permissionNames", permissionNames);
		model.addAttribute("permissionIds", permissionIds);
		model.addAttribute("role", role);
		model.addAttribute("zNodes", zNodes);
		return "role/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Model model,Role role,String permissionIds){
		JSONArray permissionArr = JSONArray.fromObject(permissionIds);
		List<RoleAndPermission> roleAndPermissions = new LinkedList<RoleAndPermission>();
		//{id:  ,visit:   ,add:   ,update:   ,delete:   }
		for (int i = 0; i < permissionArr.size(); i++) {
			JSONObject permission = permissionArr.getJSONObject(i);
			roleAndPermissions.add(new RoleAndPermission(role.getId(),new Permission(Integer.parseInt(permission.getString("id")), null, null),permission.getBoolean("visit"),permission.getBoolean("add"),permission.getBoolean("update"),permission.getBoolean("delete")));
		}
		role.setRoleAndPermissions(roleAndPermissions);
		model.addAttribute("msg", roleService.update(role));
		return "../../result";
	}
}
