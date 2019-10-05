package com.wlt.deviceledger.service.user;

import java.util.List;

import com.wlt.deviceledger.bean.user.UserBaseMapperDempBean;
import com.wlt.deviceledger.bean.user.UserBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:19:57 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IUserService {

	List<UserBean> findUserList();

	List<UserBaseMapperDempBean> baseMapperDemo();

	void testAsync1(String string);

	void testAsync2(String string);

	void add(UserBaseMapperDempBean bean);

}
 