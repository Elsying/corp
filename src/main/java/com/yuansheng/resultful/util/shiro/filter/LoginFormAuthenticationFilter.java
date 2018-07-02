package com.yuansheng.resultful.util.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * 认证拦截器
 *
 */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {


	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//		//在这里进行验证码的校验
//		//从session获取正确验证码
//		String validateCode = (String) SecurityUtils.getSubject().getSession().getAttribute("verifyCode");
//		System.out.println("-------验证码--------------"+validateCode);
//        HttpServletRequest requ=(HttpServletRequest) request; 
//		//取出页面的验证码
//		//输入的验证和session中的验证进行对比   sddsdsssssssssss
//		String randomcode = requ.getParameter("randomcode");
//		System.out.println("-------randomcode--------------"+randomcode);
//
////		if (randomcode != null && validateCode != null && !randomcode.equals(validateCode)) {
////			System.out.println("-------校验失败--------------"+randomcode);
////			//如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
////			SecurityUtils.getSubject().getSession().setAttribute("shiroLoginFailure", failureKeyAttribute);
////			//拒绝访问，不再校验账号和密码
////			return true;
////		}
//		System.out.println("-------默认表单--------------"+randomcode);
		return super.onAccessDenied(request, response);
	}
}

