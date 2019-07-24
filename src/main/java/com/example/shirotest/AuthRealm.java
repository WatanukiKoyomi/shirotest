package com.example.shirotest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.shirotest.model.Permission;
import com.example.shirotest.model.Role;
import com.example.shirotest.model.User;
import com.example.shirotest.service.UserService;

public class AuthRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
		List<String> permissionList = new ArrayList<>();
		List<String> roleNameList = new ArrayList<>();
		Set<Role> roleSet = user.getRoles();
		if(CollectionUtils.isEmpty(roleSet)) {
			for(Role role : roleSet) {
				roleNameList.add(role.getRname());
				Set<Permission> permissionSet = role.getPermission();
				if(CollectionUtils.isEmpty(permissionSet)) {
					for(Permission permission : permissionSet) {
						permissionList.add(permission.getName());
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionList);
		info.addRoles(roleNameList);
		return info;
	}

	//认证登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
		String username = passwordToken.getUsername();
		User user = userService.findByUsername(username);
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
	}

}
