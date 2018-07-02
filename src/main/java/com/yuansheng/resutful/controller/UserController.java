package com.yuansheng.resutful.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Producer;
import com.yuansheng.resultful.common.JsonResult;
import com.yuansheng.resultful.domain.Order;
import com.yuansheng.resultful.domain.UUser;
import com.yuansheng.resultful.service.RoleService;
import com.yuansheng.resultful.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private Producer kaptchaProducer=null;  
	  
    @Autowired  
    public void setCaptchaProducer(Producer kaptchaProducer) {  
        this.kaptchaProducer = kaptchaProducer;  
    }   
    @Autowired
    private UserService userService;    
    @Autowired
	private RoleService roleService;
    
    
	// 登录页面
	@RequestMapping(value = "/loginpage", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView login() {
		ModelAndView view = new ModelAndView("login");
		return view;
	}

	// 主页面
	@RequestMapping(value = "/index", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		return view;
	}

	// 添加用户页面
	@RequestMapping(value = "/addpage", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView addpage() {
		ModelAndView view = new ModelAndView("admin-add");
		// 获取所拥有的角色
		view.addObject("rolename", roleService.selectRoleByAll());
		return view;
	}


	// 登录验证
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String logins(UUser user,HttpServletRequest request) {	
		
		String validateCode = (String) SecurityUtils.getSubject().getSession().getAttribute("verifyCode");		
		String randomcode = request.getParameter("randomcode");
		if(!randomcode.equalsIgnoreCase(validateCode)){
			return JsonResult.getInstance(500, "验证码错误").toString();
		}	
		// 1.创建当前登录操作用户
		Subject subject = SecurityUtils.getSubject();
		// 2.创建token令牌，token中有用户提交的认证信息即账号和密码
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			subject.login(token);
			//subject.getSession(true);
		} catch (Exception e) {
			return JsonResult.getInstance(500, "账号或密码错误").toString();
		}
			
		return JsonResult.getInstance(200, "登录成功").toString();
	}
	
	
	//获取验证码
	@RequestMapping(value="getVCode",method=RequestMethod.GET)
	public ModelAndView  getVCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
			 response.setDateHeader("Expires",0);  
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
	        response.setHeader("Pragma", "no-cache");  
	        response.setContentType("image/jpeg");  
	        String capText = kaptchaProducer.createText();  	        
      
	        //存入Shiro会话session  
	        SecurityUtils.getSubject().getSession().setAttribute("verifyCode", capText);
	        BufferedImage bi = kaptchaProducer.createImage(capText);  
	        ServletOutputStream out = response.getOutputStream();  
	        ImageIO.write(bi, "jpg", out);  
	        try {  
	            out.flush();  
	        } finally {  
	            out.close();  
	        }  
	        return null;  
	}
	
	
	//分页显示管理员列表
	@RequestMapping(value = "/showAllUser", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public ModelAndView toAllpage(
			@RequestParam(value = "currentPage", defaultValue = "1", required = false) Integer currentPage) {
		ModelAndView view = new ModelAndView("admin-list");
		PageHelper.startPage(currentPage, 3);
		List<UUser>userlist = userService.selectUserByAll();
		PageInfo<UUser> p = new PageInfo<UUser>(userlist);
		view.addObject("pagemsg", p);;
		return view;

	}
	
	//添加管理员
	@RequestMapping(value = "/insertuser", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String insertuser(UUser user) {
		String status=userService.insertuserSelective(user);
		return  JsonResult.getInstance(200, status).toString();
	}
	
	

	
	
}
