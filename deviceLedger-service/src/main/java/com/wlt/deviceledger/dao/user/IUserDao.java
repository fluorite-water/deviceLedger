package com.wlt.deviceledger.dao.user;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlt.deviceledger.bean.user.UserBaseMapperDempBean;
import com.wlt.deviceledger.bean.user.UserBean;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:19:37 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public interface IUserDao extends BaseMapper<UserBaseMapperDempBean>{

	List<UserBean> findUserList();
	

}
 