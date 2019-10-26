package com.wlt.deviceledger.bean.systemManager;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/** 
* @Author 作者: Zhaoyongbing
* @Create at 创建时间：2019年10月22日 下午9:04:33 
* @Description 类说明 ：
* @version 版本：1.0
* @since JDK 1.8.0_181
*/
@Data
@TableName("tbl_dept")
public class DeptBean implements Serializable{

	/**
	 * xlh
	 */
	private static final long serialVersionUID = 7221622679681095817L;

	private Integer id;
	
	private Integer pid;
	
	private String deptName;
	
	@TableField(exist = false)
	private List<DeptBean> children;

}
 