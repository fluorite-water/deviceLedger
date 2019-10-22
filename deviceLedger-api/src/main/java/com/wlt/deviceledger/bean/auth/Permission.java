package com.wlt.deviceledger.bean.auth;


import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName: Permission
 * Describe: TODO
 *
 * @Date: 2019/10/7 18:46
 * @Author: 杨开怀
 */
@Data
@TableName("tbl_permission")
public class Permission implements Serializable{

    /**
	 * 序列化
	 */
	private static final long serialVersionUID = -3893523947354816754L;

	private Integer id;

    private String pid;
    
    private String url;
    
    private String routeName;
    
    private String routeIcon;
    
    @TableField(exist = false)
    private String perUrl;
    
    @TableField(exist = false)
    private List<Permission> children;

}
