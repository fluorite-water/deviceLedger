package com.wlt.deviceledger.bean.systemManager;


import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月22日 下午9:53:39 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Data
@TableName("tbl_dist_role")
public class RoleBean {
	private Integer id;
	private String roleName;

}
 