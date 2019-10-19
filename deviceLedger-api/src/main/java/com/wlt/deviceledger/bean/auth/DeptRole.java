package com.wlt.deviceledger.bean.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * ClassName: Role
 * Describe: TODO
 *
 * @Date: 2019/10/7 18:45
 * @Author: 杨开怀
 */
@Data
@TableName("tbl_dept_role")
public class DeptRole {

    private Integer id;

    private String role;

    @TableField(exist = false)
    private List<Permission> permissions;
}
