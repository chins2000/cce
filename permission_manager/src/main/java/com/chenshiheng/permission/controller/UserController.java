package com.chenshiheng.permission.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenshiheng.permission.dto.AjaxResult;
import com.chenshiheng.permission.dto.Page;
import com.chenshiheng.permission.dto.Subject;
import com.chenshiheng.permission.entity.Menu;
import com.chenshiheng.permission.entity.Role;
import com.chenshiheng.permission.entity.User;
import com.chenshiheng.permission.enums.PermissionEnum;
import com.chenshiheng.permission.service.MenuService;
import com.chenshiheng.permission.service.RoleService;
import com.chenshiheng.permission.service.UserService;
import com.chenshiheng.permission.uitls.UsersManager;

@Controller
@RequestMapping("/user")
public class UserController {

	private final String slat = "3itihweffq28d./,df;9f";
	
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name="menuServiceImpl")
	private MenuService menuService;
	
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult<Object> login(User user, HttpSession httpSession,HttpServletResponse response) {
		if(user.getUsername()!=null && user.getPassword()!=null){
			Subject subject = userService.login(user);
			if(subject==null)
				return new AjaxResult<Object>("error");
			if(UsersManager.containsSubject(subject))
				return new AjaxResult<Object>("error","登录失败,已经被登录");
			List<Menu> menus = new ArrayList<Menu>();
			if(!subject.getPermissionIds().isEmpty()){
				menus = menuService.queryListByPerId(subject.getPermissionIds());
			}
			Cookie md5cookie = new Cookie("md5",subject.getMD5(slat));
			Cookie idcookie = new Cookie("id",String.valueOf(subject.getId()));
			httpSession.setAttribute("subject", subject);
			httpSession.setAttribute("menus", menus);
			response.addCookie(md5cookie);
			response.addCookie(idcookie);
			UsersManager.add(subject);
			return new AjaxResult<Object>("success");
		}
		return new AjaxResult<Object>("error");
	}
	
	@RequestMapping(value = "/tologin", method = RequestMethod.GET)
	public String tologin() {
		return "../../login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String tologout(HttpSession session) {
		if(session!=null){
			Subject subject = (Subject) session.getAttribute("subject");
			if(subject!=null)
				UsersManager.remove(subject);
		}
		return "../../login";
	}
	
	@RequestMapping(value = "/toindex", method = RequestMethod.GET)
	public String toindex(HttpSession session ,Model model) {
		model.addAttribute("menus", session.getAttribute("menus"));
		return "../../index";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model, HttpSession httpSession,int permissionIdMark){
		Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}
		List<User> list = new LinkedList<User>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("user", null);
		map.put("page", page);
		list=userService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("permissionIdMark", permissionIdMark);
		model.addAttribute("page", page);
		return "user/list";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model, HttpSession httpSession,int permissionIdMark,String search){
		Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}
		List<User> list = new LinkedList<User>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("user", new User(search,search));
		map.put("page", page);
		list=userService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("permissionIdMark", permissionIdMark);
		model.addAttribute("page", page);
		return "user/list";
	}
	
	@RequestMapping(value="/searchpage",method=RequestMethod.GET)
	public String searchpage(Model model, HttpSession httpSession,int permissionIdMark,String search,Page page){
		Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}
		List<User> list = new LinkedList<User>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("user", new User(search,search));
		page.count();
		map.put("page", page);
		list=userService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("permissionIdMark", permissionIdMark);
		model.addAttribute("page", page);
		return "user/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult<Object> delete(int id){
		AjaxResult<Object> result = new AjaxResult<Object>();
		if(userService.delete(id)>0){
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
		if(userService.deleteMore(list)>0){
			result.setResult("success");
		}else{
			result.setResult("error");
		}
		return result;
	}
	
	@RequestMapping(value="/toadd")
	public String toadd(Model model){
		List<Role> roleList = roleService.getList();
		List<String> realGroup = new LinkedList<String>();
		for(Role role : roleList){
			if(!(role.getGroup()==null || role.getGroup().equals("")))
				if(!realGroup.contains(role.getGroup()))
					realGroup.add(role.getGroup());
		}
		StringBuilder zNodesBuilder = new StringBuilder();
		String zNodes ="";
		zNodesBuilder.append("[");
		for(String group : realGroup){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:\""+group+"\",");
			zNodesBuilder.append("pId:0,");
			zNodesBuilder.append("name:\""+group+"\",");
			zNodesBuilder.append("nocheck: true");
			zNodesBuilder.append("},");
		}
		for(Role role : roleList){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:"+role.getId()+",");
			if(role.getGroup()==null || role.getGroup().equals("")){
				zNodesBuilder.append("pId:0,");
			}else{
				zNodesBuilder.append("pId:\""+role.getGroup()+"\",");
			}
			zNodesBuilder.append("name:\""+role.getName()+"\"");
			zNodesBuilder.append("},");
		}
		zNodes=zNodesBuilder.substring(0, zNodesBuilder.length()-1);
		zNodes=zNodes+"]";
		model.addAttribute("zNodes", zNodes);
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,User user,String roleIds){
		String[] roleIdArr = roleIds.split(",");
		List<Role> roles = new LinkedList<Role>();
		
		for(String roleId : roleIdArr){
			roles.add(new Role(Integer.parseInt(roleId)));
		}
		user.setRoles(roles);
		model.addAttribute("msg", userService.add(user));
		return "../../result";
	}
	
	@RequestMapping(value="/toupdate")
	public String toupdate(Model model,String id){
		List<Role> roleList = roleService.getList();
		List<String> realGroup = new LinkedList<String>();
		User user = userService.findById(Integer.parseInt(id));
		for(Role role : roleList){
			if(!(role.getGroup()==null || role.getGroup().equals("")))
				if(!realGroup.contains(role.getGroup()))
					realGroup.add(role.getGroup());
		}
		StringBuilder zNodesBuilder = new StringBuilder();
		String zNodes ="";
		zNodesBuilder.append("[");
		for(String group : realGroup){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:\""+group+"\",");
			zNodesBuilder.append("pId:0,");
			zNodesBuilder.append("name:\""+group+"\",");
			zNodesBuilder.append("nocheck: true");
			zNodesBuilder.append("},");
		}
		for(Role role : roleList){
			zNodesBuilder.append("{");
			zNodesBuilder.append("id:"+role.getId()+",");
			if(role.getGroup()==null || role.getGroup().equals("")){
				zNodesBuilder.append("pId:0,");
			}else{
				zNodesBuilder.append("pId:\""+role.getGroup()+"\",");
			}
			if(user.getRoles().contains(role)){
				zNodesBuilder.append("checked:true,");
			}
			zNodesBuilder.append("name:\""+role.getName()+"\"");
			zNodesBuilder.append("},");
		}
		zNodes=zNodesBuilder.substring(0, zNodesBuilder.length()-1);
		zNodes=zNodes+"]";
		StringBuilder roleIds = new StringBuilder();
		StringBuilder roleNames = new StringBuilder();
		for(Role role : user.getRoles()){
			roleIds.append(role.getId()+",");
			roleNames.append(role.getName()+",");
		}
		model.addAttribute("zNodes", zNodes);
		model.addAttribute("user", user);
		System.out.println(user);
		if(!user.getRoles().isEmpty()){
			model.addAttribute("roleIds", roleIds.substring(0, roleIds.length()-1));
			model.addAttribute("roleNames", roleNames.substring(0, roleIds.length()-1));
			System.out.println(roleNames.substring(0, roleIds.length()-1));
		}else{
			model.addAttribute("roleIds", "");
			model.addAttribute("roleNames", "");
		}
		return "user/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Model model,User user,String roleIds){
		String[] roleIdArr = roleIds.split(",");
		List<Role> roles = new LinkedList<Role>();
		
		for(String roleId : roleIdArr){
			roles.add(new Role(Integer.parseInt(roleId)));
		}
		user.setRoles(roles);
		model.addAttribute("msg", userService.update(user));
		return "../../result";
	}
	
}
