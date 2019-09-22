package com.wlt.deviceledger.bean.user;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月21日 下午5:11:06 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@TableName(value = "users")//指定表名
public class UserBaseMapperDempBean implements Serializable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -427760430705956356L;
	//value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
//    @TableId(value = "id",type = IdType.AUTO)//指定自增策略
//    private Integer id;
    //若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
//    @TableField(value = "userId",exist = true)
    @TableField(value = "userId")
    private String userId;
    @TableField(value = "username")
	private String username;
    @TableField(value = "age")
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
 