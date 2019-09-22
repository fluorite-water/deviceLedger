package com.wlt.deviceledger.bean.user;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:40:21 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
public class UserBean extends Model<UserBean> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 396085214682707224L;
	private String userId;
	private String username;
	private String age;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

}
 