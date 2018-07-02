package com.yuansheng.resutful.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuansheng.resultful.common.JsonResult;
import com.yuansheng.resultful.domain.Order;
import com.yuansheng.resultful.service.OrderService;

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

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	//默认登录页
	@RequestMapping(value = "")
	public String editUser() {
		return "login";
	}
	
	//主页面
	@RequestMapping(value = "/index", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
		return view;
	}

	//分页显示列表
	@RequestMapping(value = "/showAllOrder", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView toAllpage(@RequestParam(value="currentPage",defaultValue="1",required=false)Integer currentPage){
		ModelAndView view = new ModelAndView("order-list");
		PageHelper.startPage(currentPage,3);
		List<Order> orderlist=orderService.selectByAll();
		PageInfo<Order> p=new PageInfo<Order>(orderlist);
		view.addObject("pagemsg",p);
		view.addObject("orderlist",orderlist);
		return view;
		
	}
	
	
	
	// 删除指定id订单， @PathVariable：获取url里面的一个值
	@RequestMapping(value = "/delete/{orderId}", method = RequestMethod.DELETE, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("orderId")int id){	
		JsonResult jsonResult = JsonResult.getInstance(orderService.deleteByPrimaryKey(id), "删除用户");
		return jsonResult.toString();
	}
	
	
	
//	// 修改某个用户的信息
//		@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
//		@ResponseBody
//		public String updateUser(@PathVariable("userId") String userId,String name,Integer sex) {
//			System.out.println(userId+"  "+name+" "+sex);
//			JsonResult jsonResult = JsonResult.getInstance(0, "修改成功");
//			return jsonResult.toString();
//		}
	
	
	//测试显示第一条数据
	@RequestMapping(value="/selset")
	public String select(Model model){
		model.addAttribute("oder1",orderService.selectByPrimaryKey(1));
		return "hello";
	}

	

}
