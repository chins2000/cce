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
import com.chenshiheng.permission.enums.PermissionEnum;
import com.chenshiheng.permission.service.PermissionService;


@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Resource(name="permissionServiceImpl")
	private PermissionService permissionService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model, HttpSession httpSession){
		/*Subject subject = (Subject)httpSession.getAttribute("subject");
		if(!subject.hasPermission(permissionIdMark, PermissionEnum.VISIT)){
			model.addAttribute("msg","不存在的权限");
			return "../../error";
		}*/
		List<Permission> list = new LinkedList<Permission>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("permission", null);
		map.put("page", page);
		list=permissionService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("page", page);
		return "permission/list";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model, HttpSession httpSession,String search){
		
		List<Permission> list = new LinkedList<Permission>();
		Map<String, Object> map = new HashMap<String,Object>();
		Page page = new Page();
		map.put("permission", new Permission(search,search));
		map.put("page", page);
		list=permissionService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("page", page);
		return "permission/list";
	}
	
	@RequestMapping(value="/searchpage",method=RequestMethod.GET)
	public String searchpage(Model model, HttpSession httpSession,String search,Page page){
		List<Permission> list = new LinkedList<Permission>();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("permission", new Role(search,search));
		page.count();
		map.put("page", page);
		list=permissionService.queryListByPage(map);
		model.addAttribute("list",list);
		model.addAttribute("page", page);
		return "permission/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult<Object> delete(int id){
		AjaxResult<Object> result = new AjaxResult<Object>();
		if(permissionService.delete(id)>0){
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
		if(permissionService.deleteMore(list)>0){
			result.setResult("success");
		}else{
			result.setResult("error");
		}
		return result;
	}
	
	@RequestMapping(value="/toadd")
	public String toadd(Model model){
		return "permission/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,Permission permission){
		model.addAttribute("msg", permissionService.add(permission));
		return "../../result";
	}
	
	@RequestMapping(value="/toupdate")
	public String toupdate(Model model,String id){
		Permission permission = permissionService.findById(Integer.parseInt(id));
		model.addAttribute("permission", permission);
		return "permission/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Model model,Permission permission){
		model.addAttribute("msg", permissionService.update(permission));
		return "../../result";
	}
	
	
	
}
