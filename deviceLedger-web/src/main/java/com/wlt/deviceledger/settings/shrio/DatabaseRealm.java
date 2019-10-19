package com.wlt.deviceledger.settings.shrio;

import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.auth.Role;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.auth.IRoleService;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.common.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("————权限认证————");
		String username = JWTUtil.getUsername(principals.toString());
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 此处最好使用缓存提升速度
		UserBean userBean = userService.getUserByLoginAct(username);
		userBean = userService.getUserOfRole(userBean.getId());
		if (userBean == null || userBean.getRoleList().isEmpty()) {
			return authorizationInfo;
		}
		for (Role role : userBean.getRoleList()) {
			authorizationInfo.addRole(role.getRole());
			role = roleService.getRoleOfPerm(role.getId());
			if (role == null || role.getPermissions().isEmpty()) {
				continue;
			}
			for (Permission p : role.getPermissions()) {
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("————身份认证方法————");
		String token = (String) authenticationToken.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);
		if (username == null || !JWTUtil.verify(token, username)) {
			throw new AuthenticationException("token认证失败！");
		}
		UserBean userInfo = userService.getUserByLoginAct(username);
		if (userInfo == null) {
			throw new AuthenticationException("该用户不存在！");
		}
		if (userInfo.getState() == 1) {
			throw new AuthenticationException("该用户已被封号！");
		}

		if (userInfo.getIsDelete() == 1) {
			throw new AuthenticationException("该用户已被注销！");
		}
		return new SimpleAuthenticationInfo(token, token, "MyRealm");
	}



}