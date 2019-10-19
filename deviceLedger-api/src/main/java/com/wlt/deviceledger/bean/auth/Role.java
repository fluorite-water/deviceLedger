package com.wlt.deviceledger.bean.auth;

import lombok.Data;

import java.util.List;

/**
 * ClassName: Role
 * Describe: TODO
 *
 * @Date: 2019/10/13 14:58
 * @Author: 杨开怀
 */
@Data
public class Role {

    private Integer id;

    private String deptCode;

    private String role;

    private List<Permission> Permissions;


}
