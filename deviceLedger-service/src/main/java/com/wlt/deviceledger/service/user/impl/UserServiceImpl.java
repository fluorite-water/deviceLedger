package com.wlt.deviceledger.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.dao.auth.IRoleDao;
import com.wlt.deviceledger.dao.user.IUserDao;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import com.wlt.deviceledger.util.common.DateUtil;
import com.wlt.deviceledger.util.common.JWTUtil;
import com.wlt.deviceledger.util.config.UserToken;
import com.wlt.deviceledger.util.enums.CommonEnum;
import com.wlt.deviceledger.util.exception.user.UserException;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:20:26 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService{

	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao userDao;


	@Autowired
	private IRoleDao roleDao;



	@Override
//	@Async
	public List<UserBean> findUserList() {
//		QueryWrapper<UserBean> emptryWrapper = new QueryWrapper<UserBean>();
//		emptryWrapper.setEntity(new UserBean());
//		return userDao.selectList(emptryWrapper);
		return userDao.findUserList();
	}
	/*@Override
	@Async
	public List<UserBaseMapperDempBean> baseMapperDemo() {
		QueryWrapper<UserBaseMapperDempBean> queryWrapper = new QueryWrapper();
		return userDao.selectList(queryWrapper);
	}
	@Override
	@Async
	public void testAsync1(String string) {
		System.out.println(string);
		System.out.println("---------------------------");
	}
	@Override
	@Async
	public void testAsync2(String string) {
		System.out.println(string);
		System.out.println("**************************");
	}
	@Override
	@Transactional
	public void add(UserBaseMapperDempBean bean) {
		userDao.insert(bean);
	}
*/

	@Override
	public UserBean getUserByLoginAct(String loginAct) {
		UserBean userBean = new UserBean();
		userBean.setLoginAct(loginAct);
		QueryWrapper<UserBean> queryWrapper = new QueryWrapper();
		queryWrapper.setEntity(userBean);
		return userDao.selectOne(queryWrapper);
	}

	@Override
	public UserBean getUserOfRole(String id) {
		return userDao.selectUserOfRole(id);
	}


	/**
	 * 登录
	 * @param userBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> login(UserBean userBean) throws Exception {

		String loginAct = userBean.getLoginAct();//账号
		String loginPwd = userBean.getLoginPwd();//密码

		Map<String, Object> returnMap = new HashMap<>();;

		UserBean loginActUserBean = new UserBean();
		loginActUserBean.setLoginAct(loginAct);
		QueryWrapper<UserBean> loginActUserBeanQueryWrapper = new QueryWrapper<>();
		loginActUserBeanQueryWrapper.setEntity(loginActUserBean);


		UserBean selectOneUserBean = userDao.selectOne(loginActUserBeanQueryWrapper);
		if(selectOneUserBean == null) {
			throw new UserException("账号不存在");
		}

		String salt = selectOneUserBean.getSalt();

		String passwordInDB = userBean.getLoginPwd();

		int times = 2;
		//MD5加密方式
		String algorithmName = ConstantUtils.MD5_TYPE;
		//加盐
		String encodedPassword = new SimpleHash(algorithmName, loginPwd, salt, times).toString();

		QueryWrapper<UserBean> userBeanQueryWrapper = new QueryWrapper<>();

		UserBean checkUser = new UserBean();
		checkUser.setLoginAct(loginAct);
		checkUser.setLoginPwd(encodedPassword);

		userBeanQueryWrapper.setEntity(checkUser);

		UserBean user = userDao.selectOne(userBeanQueryWrapper);

		if(user == null) {
			throw new UserException("密码错误");
		}

		if(user.getLoginPwd().equals(encodedPassword)) {

			String token = JWTUtil.createToken(loginAct);

            UserToken shrio = new UserToken(loginAct, loginPwd ,token);

			Subject subject = SecurityUtils.getSubject();

			subject.getSession().setAttribute("user", user);

			try {
				subject.login(shrio);
			} catch (UnknownAccountException uae) {
				ExceptionConstantsUtils.printSysErrorMessage(log, uae, "用户名和密码不匹配");
				throw new UserException("用户名和密码不匹配");
			} catch (IncorrectCredentialsException ice) {
				ExceptionConstantsUtils.printSysErrorMessage(log, ice,"用户名和密码不匹配");
				new UserException("用户名和密码不匹配");
			} catch (LockedAccountException lae) {
				ExceptionConstantsUtils.printSysErrorMessage(log, lae,"LockedAccountException");
				throw new UserException("LockedAccountException");
			} catch (ExcessiveAttemptsException eae) {
				ExceptionConstantsUtils.printSysErrorMessage(log, eae,"ExcessiveAttemptsException");
				throw new UserException("ExcessiveAttemptsException");
			} catch (AuthenticationException ae) {
				ExceptionConstantsUtils.printSysErrorMessage(log, ae, ExceptionUtils.getMessage(ae));
				throw new UserException("AuthenticationException");
			}



			returnMap.put("token", token);
			return returnMap;
		}
		return null;
	}

	/**
	 * 获取用户信息
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserBean getUserToken(String token) throws Exception {

		UserBean userBean = new UserBean();

		String id = JWTUtil.getUsername(token);

		if(id == null) {
			throw new UserException("无效token");
		}

		userBean.setId(id);

		boolean verify = JWTUtil.verify(token, id);

		if(verify) {
			QueryWrapper<UserBean> userBeanQueryWrapper = new QueryWrapper<>();
			userBeanQueryWrapper.setEntity(userBean);

			UserBean selectUserBean = userDao.selectOne(userBeanQueryWrapper);

			if(selectUserBean == null) {
				throw new UserException("用户名或者密码错误");
			}

			return selectUserBean;
		}
		return null;
	}


	/**
	 * 注册
	 * @param userBean
	 * @return
	 */
	@Override
	public Boolean regis(UserBean userBean) throws Exception {

		String loginPwd = userBean.getLoginPwd();


		if(loginPwd == null || loginPwd.equals("")) {
			throw new UserException("请填写密码");
		}

		//盐
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();

		int times = 2;
		//MD5加密方式
		String algorithmName = ConstantUtils.MD5_TYPE;
		//加盐
		String encodedPassword = new SimpleHash(algorithmName, loginPwd, salt, times).toString();

		userBean.setLoginPwd(encodedPassword);
		userBean.setSalt(salt);
		userBean.setCreateTime(DateUtil.getCurrenDateTime());
		userBean.setState(CommonEnum.RIGET.getCode());
		userBean.setIsDelete(CommonEnum.RIGET.getCode());
		try {
			userDao.insert(userBean);
		} catch (Exception e) {
			ExceptionConstantsUtils.printSysErrorMessage(log, e, "注册用户失败");
			throw new UserException("注册用户失败");
		}

		return true;
	}

	@Override
	public List<Permission> getPerOfUserId(String id) {
		
		return userDao.getPerOfUserId(id);
	}


}
 