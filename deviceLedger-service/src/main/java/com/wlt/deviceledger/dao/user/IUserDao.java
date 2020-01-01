package com.wlt.deviceledger.dao.user;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.user.UserBean;


/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:19:37 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IUserDao extends BaseMapper<UserBean>{

	List<UserBean> findUserList();

    /**
     * 查询用户角色
     * @param id
     * @return
     */
    UserBean selectUserOfRole(String id);

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
	 * @throws Exception
	 */
	List<Permission> findMenueList(String id)throws Exception;

	/**
	 * 根据账号 查询资源
	 * @param loginAct
	 * @return
	 */
	List<Permission> selectPerOfLoginAct(String loginAct);

	/**
	 * 根据角色id 查资源list
	 * @param roleId
	 * @return
	 */
	List<Permission> roleManue(Integer roleId)throws Exception;

	/**
	 * 查询所有菜单
	 * @return
	 */
	List<Permission> manueAll() throws Exception;

	/**
	 * 查询用户列表
	 * @param userBean
	 * @return
	 */
	List<Map<String, Object>> selectUserList(UserBean userBean);
}
 