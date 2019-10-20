package com.wlt.deviceledger.bean.user;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wlt.deviceledger.bean.auth.Role;
import lombok.Data;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年9月15日 下午4:40:21 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/

@Data
@TableName("tbl_user")
public class UserBean {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 396085214682707224L;
	private String id;
	private String userName;
	private String loginAct;
	private String loginPwd;
	private String salt;
	private Integer state;
	private Integer isDelete;
	private String createTime;
	private String email;


	@TableField(exist = false)
	private String kaptcha;

	@TableField(exist = false)
	private String token;

	@TableField(exist = false)
	private List<Role> roleList;

}
 