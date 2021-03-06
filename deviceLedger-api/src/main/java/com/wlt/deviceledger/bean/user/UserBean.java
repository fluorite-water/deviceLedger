package com.wlt.deviceledger.bean.user;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.wlt.deviceledger.bean.auth.Permission;
import com.wlt.deviceledger.bean.systemManager.RoleBean;

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
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 396085214682707224L;
	@TableId(value = "id" ,type = IdType.AUTO)
	private String id;
	private String userName;
	private String loginAct;
	private String loginPwd;
	private String salt;
	private Integer state;
	private Integer isDelete;
	private String createTime;
	private String email;
	private String deptId;
	private Integer roleId;
	private String token;
	private String loginTime;
	private Integer loginFlag;

	@TableField(exist = false)
	private String kaptcha;

	@TableField(exist = false)
	private List<RoleBean> roleList;

	@TableField(exist = false)
	private List<Permission> permissionList;

	@TableField(exist = false)
	private String pageSize;

	@TableField(exist = false)
	private String pageNum;


    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String roleName;
}
 