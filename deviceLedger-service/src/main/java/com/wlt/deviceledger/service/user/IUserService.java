package com.wlt.deviceledger.service.user;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.systemManager.RoleBean;
import com.wlt.deviceledger.bean.user.UserBean;
import org.springframework.cache.annotation.CachePut;
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
    Map<String, Object> login(UserBean userBean, String sessionId) throws Exception;

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
	 * @param userBean
	 * @return
	 */
	UserBean getUser(UserBean userBean);
    /**
     * 根据用户id 查询资源
     * @param id
     * @return
     */
	List<Permission> getPerOfUserId(String id);

	/**
	 * 获取资源
	 * @param id
	 * @return
	 */
	List<Permission> findMenueList(String id)throws Exception;


	/**
	 * 获取资源
	 * @param loginAct
	 * @return
	 */
	List<Permission> getPerByLoginAct(String loginAct);

	/**
	 * 添加用户
	 * @param bean
	 * @return
	 */
	ResultData<Object> addUser(UserBean bean)throws Exception;

	/**
	 * 修改用户信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	ResultData<Object> updateUser(UserBean bean)throws Exception;

	/**
	 * 获取用户列表
	 * @param userBean
	 * @return
	 */
	IPage<UserBean> getUserList(UserBean userBean);

	/**
	 * 通过用户id获取角色
	 * @param id
	 * @return
	 */
	RoleBean getRoleByRoleCode(String id);

	/*
	 * 根据角色查资源
	 * @param roleId
	 * @return
	 */
	List<Permission> roleManue(Integer roleId)throws Exception;

	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Permission> manueAll()throws Exception;
}
 