package com.wlt.deviceledger.service.user;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wlt.deviceledger.bean.user.UserBean;
import com.wlt.deviceledger.util.base.ResultData;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:19:57 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IUserService {

	List<UserBean> findUserList();

	/*
	List<UserBaseMapperDempBean> baseMapperDemo();

	void testAsync1(String string);

	void testAsync2(String string);

	void add(UserBaseMapperDempBean bean);*/

	/**
	 * 根据账号查询用户
	 * @param loginAct
	 * @return
	 */
    UserBean getUserByLoginAct(String loginAct);

	/**
	 * 查询用户对应的角色
	 * @param id
	 * @return
	 */
	UserBean getUserOfRole(String id);


    /**
     * 登录
     * @param userBean
     * @return
     * @throws Exception
     */
    Map<String, Object> login(UserBean userBean) throws Exception;

    /**
     * 获取用户信息
     * @param token
     * @return
     * @throws Exception
     */
    UserBean getUserToken(String token) throws Exception;

    /**
     * 注册
     * @param userBean
     * @return
     */
    Boolean regis(UserBean userBean) throws Exception;

	/**
	 * 根据用户名和密码查询用户
	 * @param username
	 * @param loginPwd
	 * @return
	 */
	UserBean getUser(UserBean userBean);
}
 