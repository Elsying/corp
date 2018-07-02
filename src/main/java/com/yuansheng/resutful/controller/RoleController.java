package com.yuansheng.resutful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuansheng.resultful.common.JsonResult;
import com.yuansheng.resultful.domain.URole;
import com.yuansheng.resultful.domain.UUser;
import com.yuansheng.resultful.service.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	//分页显示角色列表
	@RequestMapping(value = "/showAllRole", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView toAllpage(
			@RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage) {
		ModelAndView view = new ModelAndView("admin-list");
		PageHelper.startPage(currentPage, 3);
		List<URole> rolelist = roleService.selectRoleByAll();
		PageInfo<URole> p = new PageInfo<URole>(rolelist);
		view.addObject("pagemsg", p);
		return view;
	}
	

}
