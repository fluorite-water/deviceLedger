package com.wlt.deviceledger.settings.shrio;


import com.shrio.JWTToken;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.common.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseRealm extends AuthorizingRealm {

    @Autowired
	private IUserService userService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("————权限认证————");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 能进入到这里，表示账号已经通过验证了
		String LoginAct = (String) principals.getPrimaryPrincipal();

		/*CacheManager cacheManager= new CacheManager();

		Cache cache = cacheManager.getCache("authorizationCache");

		Element element = cache.get(LoginAct);

		//从缓存中获取用户权限，如果为空，从数据库中查
		if(element == null) {

			//查询权限getPerOfUserId
			List<Permission> perList = userService.getPerByLoginAct(LoginAct);

			Element authBean = new Element(LoginAct, perList);

			cache.put(authBean);
			Set<String> perSet = new HashSet<>();

			for(Permission permission : perList) {
				String perUrl = permission.getPerUrl();
				perSet.add(perUrl);
			}

			authorizationInfo.addStringPermissions(perSet);

			//否则从缓存中查看
		} else {
			List<Permission> objectValue = (List<Permission>) element.getObjectValue();

			Set<String> perSet = new HashSet<>();

			for(Permission permission : objectValue) {
				String perUrl = permission.getPerUrl();
				perSet.add(perUrl);
			}
			authorizationInfo.addStringPermissions(perSet);

		}*/
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("————身份认证方法————");
		//String token = String.valueOf((char[]) userToken.getCredentials());
        //System.out.println("--------->" + token);

		JWTToken jwtToken = (JWTToken) authenticationToken;
		String loginPwd = jwtToken.getLoginPwd();
		Object principal = authenticationToken.getPrincipal();
		System.out.println("--------->" + principal);

		System.out.println("————身份认证方法————");
		String token = jwtToken.getToken();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);

		if(username == null || !JWTUtil.verify(token, username)) {
		    throw new AuthenticationException("token认证失败！");
        }

		UserBean userInfo = userService.getUserByLoginAct(username);

		if(userInfo == null) {
			throw new AuthenticationException("账号不存在！");
		}
		String salt = userInfo.getSalt();
		int times = 2;
		//MD5加密方式
		String algorithmName = ConstantUtils.MD5_TYPE;
		//加盐
		String encodedPassword = new SimpleHash(algorithmName, loginPwd, salt, times).toString();

		SimpleAuthenticationInfo myRealm = new SimpleAuthenticationInfo(username, encodedPassword, ByteSource.Util.bytes(salt), "myShiroRealm");

		return myRealm;
	}

}