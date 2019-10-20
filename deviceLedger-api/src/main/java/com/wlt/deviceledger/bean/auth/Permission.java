package com.wlt.deviceledger.bean.auth;


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
public class Permission{

    private Integer id;

    private String pid;
    
    private String url;
    
    private String routeName;
    
    private String routeIcon;
    
    @TableField(exist = false)
    private String perUrl;

}
