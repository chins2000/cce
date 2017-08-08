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
import com.chenshiheng.permission.entity.Menu;
import com.chenshiheng.permission.entity.Permission;
import com.chenshiheng.permission.entity.Role;
import com.chenshiheng.permission.service.MenuService;
import com.chenshiheng.permission.service.PermissionService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Resource(name="permissionServiceImpl")
	private PermissionService permissionService;

	@Resource(name="menuServiceImpl")
	private MenuService menuService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model, HttpSession httpSession){
		/*Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(menuIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}*/
		List<Menu> list = new LinkedList<Menu>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("menu", null);
		map.put("page", page);
		list=menuService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("page", page);
		return "menu/list";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model, HttpSession httpSession,String search){
		
		List<Menu> list = new LinkedList<Menu>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("menu", new Permission(search,search));
		map.put("page", page);
		list=menuService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("page", page);
		return "menu/list";
	}
	
	@RequestMapping(value="/searchpage",method=RequestMethod.GET)
	public String searchpage(Model model, HttpSession httpSession,String search,Page page){
		List<Menu> list = new LinkedList<Menu>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("menu", new Role(search,search));
		page.count();
		map.put("page", page);
		list=menuService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("page", page);
		return "menu/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult<Object> delete(int id){
		AjaxResult<Object> result = new AjaxResult<Object>();
		if(menuService.delete(id)>0){
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
		if(menuService.deleteMore(list)>0){
			result.setResult("success");
		}else{
			result.setResult("error");
		}
		return result;
	}
	
	@RequestMapping(value="/toadd")
	public String toadd(Model model){
		
		// ------------权限下拉列表
		List<Permission> permissionList = permissionService.getList();
		List<String> realGroup = new LinkedList<String>();
		for(Permission permission : permissionList){
			if(!(permission.getGroup()==null || permission.getGroup().equals("")))
				if(!realGroup.contains(permission.getGroup()))
					realGroup.add(permission.getGroup());
		}
		StringBuilder zNodesBuilder = new StringBuilder();
		String permissionNodes ="[]";
		zNodesBuilder.append("[");
		for(String group : realGroup){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:\""+group+"\",");
			zNodesBuilder.append("pId:0,");
			zNodesBuilder.append("name:\""+group+"\"");
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
		}
		if(zNodesBuilder.length()>0){
			permissionNodes=zNodesBuilder.substring(0, zNodesBuilder.length()-1);
			permissionNodes=permissionNodes+"]";
		}
		model.addAttribute("permissionNodes", permissionNodes);
		// -------------菜单下拉列表
		List<Menu> menuList = menuService.getList();
		zNodesBuilder = new StringBuilder();
		zNodesBuilder.append("[");
		for (Menu menu : menuList) {
			zNodesBuilder.append("{");
			zNodesBuilder.append("\"pId\":"+menu.getPid()+",");
			zNodesBuilder.append("\"id\":"+menu.getId()+",");
			zNodesBuilder.append("\"name\":\""+menu.getName()+"\"");
			zNodesBuilder.append("},");
		}
		String menuNodes = "[]";
		if(zNodesBuilder.length()>0){
			menuNodes = zNodesBuilder.substring(0, zNodesBuilder.length()-1);
			menuNodes = menuNodes+ "]";
		}
		model.addAttribute("menuNodes", menuNodes);
		
		return "menu/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,Menu menu){
		model.addAttribute("msg", menuService.add(menu));
		return "../../result";
	}
	
	@RequestMapping(value="/toupdate")
	public String toupdate(Model model,String id){
		Menu current_Menu = menuService.findById(Integer.parseInt(id));
		model.addAttribute("menu", current_Menu);
		// ------------权限下拉列表
		List<Permission> permissionList = permissionService.getList();
		List<String> realGroup = new LinkedList<String>();
		for(Permission permission : permissionList){
			if(!(permission.getGroup()==null || permission.getGroup().equals("")))
				if(!realGroup.contains(permission.getGroup()))
					realGroup.add(permission.getGroup());
		}
		StringBuilder zNodesBuilder = new StringBuilder();
		String permissionNodes ="";
		zNodesBuilder.append("[");
		for(String group : realGroup){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:\""+group+"\",");
			zNodesBuilder.append("pId:0,");
			zNodesBuilder.append("name:\""+group+"\",");
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
			if(permission.getId()==current_Menu.getPermissionId()){
				model.addAttribute("arg_permission_name", permission.getName());
				model.addAttribute("arg_permission_id", permission.getId());
			}
		}
		if(zNodesBuilder.length()>0){
			permissionNodes=zNodesBuilder.substring(0, zNodesBuilder.length()-1);
			permissionNodes=permissionNodes+"]";
		}
		model.addAttribute("permissionNodes", permissionNodes);
		// ------------菜单下拉列表
		
		List<Menu> menuList = menuService.getList();
		zNodesBuilder = new StringBuilder();
		zNodesBuilder.append("[");
		for (Menu menu : menuList) {
			zNodesBuilder.append("{");
			zNodesBuilder.append("\"pId\":"+menu.getPid()+",");
			zNodesBuilder.append("\"id\":"+menu.getId()+",");
			zNodesBuilder.append("\"name\":\""+menu.getName()+"\"");
			zNodesBuilder.append("},");
			if(menu.getId()==current_Menu.getPid()){
				model.addAttribute("arg_parent_menu_name", menu.getName());
				model.addAttribute("arg_parent_menu_id", menu.getId());
			}
		}
		String menuNodes = "[]";
		if(zNodesBuilder.length()>0){
			menuNodes = zNodesBuilder.substring(0, zNodesBuilder.length()-1);
			menuNodes = menuNodes+ "]";
		}
		model.addAttribute("menuNodes", menuNodes);
		
		return "menu/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Model model,Menu menu){
		model.addAttribute("msg", menuService.update(menu));
		return "../../result";
	}
	
}
