package com.wlt.deviceledger.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shrio.JWTToken;
import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.dao.systemManager.IRoleDao;
import com.wlt.deviceledger.dao.user.IUserDao;
import com.wlt.deviceledger.service.user.IUserService;
import com.wlt.deviceledger.util.base.ConstantUtils;
import com.wlt.deviceledger.util.base.ExceptionConstantsUtils;
import com.wlt.deviceledger.util.base.ResultData;
import com.wlt.deviceledger.util.common.DateUtil;
import com.wlt.deviceledger.util.common.JWTUtil;
import com.wlt.deviceledger.util.common.MD5Util;
import com.wlt.deviceledger.util.enums.CommonEnum;
import com.wlt.deviceledger.util.exception.user.UserException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> login(UserBean userBean, String seesionId) throws Exception {

		String loginAct = userBean.getLoginAct();//账号
		String loginPwd = userBean.getLoginPwd();//密码

		loginPwd = MD5Util.getMD5(loginPwd);

		Map<String, Object> returnMap = new HashMap<>();;

		if(loginAct == null || "".equals(loginAct)) {
			throw new UserException("请输入账号");
		}

		if(loginPwd == null || "".equals(loginPwd)) {
			throw new UserException("请输入账号");
		}

		String token = JWTUtil.createToken(loginAct);

		JWTToken shrio = new JWTToken(token);
		shrio.setLoginPwd(loginPwd);
		shrio.setUsername(loginAct);

		UserBean userInfo = getUserByLoginAct(loginAct);

		String salt = userInfo.getSalt();
		int times = 2;
		//MD5加密方式
		String algorithmName = ConstantUtils.MD5_TYPE;
		//加盐
		String encodedPassword = new SimpleHash(algorithmName, loginPwd, salt, times).toString();

		UserBean queryUserBean = new UserBean();
		queryUserBean.setLoginAct(loginAct);
		queryUserBean.setLoginPwd(encodedPassword);
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.setEntity(queryUserBean);//07ae6504f35a6a77333074f5e40d5a14
		UserBean selUserBean = userDao.selectOne(queryWrapper);
		if (selUserBean.getState() == 1) {
			throw new AuthenticationException("该用户已被封号！");
		}

		if (selUserBean.getIsDelete() == 1) {
			throw new AuthenticationException("该用户已被注销！");
		}
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(shrio);
		} catch (UnknownAccountException uae) {
			ExceptionConstantsUtils.printSysErrorMessage(log, uae, "用户名和密码不匹配");
			throw new UserException("用户名和密码不匹配");
		} catch (IncorrectCredentialsException ice) {
			ExceptionConstantsUtils.printSysErrorMessage(log, ice,"用户名和密码不匹配");
			throw new UserException("用户名和密码不匹配");
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
		returnMap.put("seesionId", seesionId);
		return returnMap;
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

		userBean.setLoginAct(id);

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

		loginPwd = MD5Util.getMD5(loginPwd);

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
	public UserBean getUser(UserBean userBean) {

		QueryWrapper<UserBean> userBeanQueryWrapper = new QueryWrapper<>();
		userBeanQueryWrapper.setEntity(userBean);
		return userDao.selectOne(userBeanQueryWrapper);

	}

	public List<Permission> getPerOfUserId(String id) {
		
		return userDao.getPerOfUserId(id);
	}

	@Override
	public List<Permission> findMenueList(String id) throws Exception {
		
		return userDao.findMenueList(id);
	}

	@Override
	public List<Permission> getPerByLoginAct(String loginAct) {
		return userDao.selectPerOfLoginAct(loginAct);
	}

	@Override
	public ResultData<Object> addUser(UserBean bean) throws Exception {
		ResultData<Object> res = new ResultData<Object>();
		// 查询账号是否已被使用
		QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
		UserBean userBean = new UserBean();
		userBean.setLoginAct(bean.getLoginAct());
		queryWrapper.setEntity(userBean);
		UserBean selectOne = userDao.selectOne(queryWrapper);
		if(selectOne == null) {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("该账号已被使用，请更换账号");
			res.setSuccess(false);
			return res;
		}
		// 账号可用
		int u = userDao.insert(bean);
		if(u > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("用户添加成功");
			res.setSuccess(true);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("用户添加失败，请检查网络");
			res.setSuccess(false);
		}
		return res;
	}

	@Override
	public ResultData<Object> updateUser(UserBean bean) throws Exception {
		
		ResultData<Object> res = new ResultData<Object>();
		// 查询账号是否已被使用
		QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
		UserBean userBean = new UserBean();
		userBean.setLoginAct(bean.getLoginAct());
		queryWrapper.setEntity(userBean);
		UserBean selectOne = userDao.selectOne(queryWrapper);
		if(selectOne == null) {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("该账号已被使用，请更换账号");
			res.setSuccess(false);
			return res;
		}
		// 账号可用
		int u = userDao.updateById(bean);
		if(u > 0) {
			res.setCode(ConstantUtils.SUCCESS_CODE);
			res.setMsg("用户信息修改成功");
			res.setSuccess(true);
		}else {
			res.setCode(ConstantUtils.ERROR_CODE);
			res.setMsg("用户信息修改失败，请检查网络");
			res.setSuccess(false);
		}
		return res;
	}

	@Override
	public List<Permission> roleManue(Integer roleId) throws Exception {
		return userDao.roleManue(roleId);
	}

	@Override
	public List<Permission> manueAll() throws Exception{
		
		return userDao.manueAll();
	}


}
 