package com.wlt.deviceledger.bean.auth;

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
public class Permission {

    private Integer id;

    private String permission;
}
