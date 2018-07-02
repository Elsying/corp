package com.yuansheng.resultful.util.shiro.token;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuansheng.resultful.domain.UUser;
import com.yuansheng.resultful.service.UserService;

public class MyRealm extends AuthorizingRealm  {
    @Autowired
    private UserService userService; 
	
    
    /**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//		//获取主身份
//		String username = arg0.getPrimaryPrincipal().toString() ;
//		//一个存放身份的set
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
//        
//        //数据库查询结果
//        Set<String> roleName = userService.findRoles(username);
//        Set<String> permissions = userService.findPermissions(username);
//        
//        
//        //进行授权角色
//        info.setRoles(roleName);
//        //进行授权权限
//        info.setStringPermissions(permissions);	
        
        return null;
	}

	
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		//获取用户账号
		String userName=(String)arg0.getPrincipal();//获取这个principalcollection的第一个元素
		// UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // String userName = upToken.getUsername();        
		System.out.println("-------------"+userName);
		//根据用户名查找用户信息 数据库里找
        UUser user=userService.findByUsername(userName);
        if (user != null){
        	System.out.println("-------------认证：当前登录的用户存在------------");
            //将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数传入realName可以重写改方法。
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
			return authcInfo;
		} else {
			// 用户名不存在抛出异常
			System.out.println("认证：当前登录的用户不存在");
			throw new UnknownAccountException();
        }
	}

}
