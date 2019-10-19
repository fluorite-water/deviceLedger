package com.wlt.deviceledger.service.auth;

import com.wlt.deviceledger.bean.auth.Role;

import java.util.Set;

/**
 * ClassName: IRoleService
 * Describe: TODO
 *
 * @Date: 2019/10/7 17:43
 * @Author: 杨开怀
 */
public interface IRoleService {

    /**
     * 根据账号查询角色
     * @param loginAct
     * @return
     */
    Set<String> getRoleListByLoginAct(String loginAct);


    /**
     * 查询角色下的权限
     * @param id
     * @return
     */
    Role getRoleOfPerm(Integer id);
}
