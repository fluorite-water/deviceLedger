package com.wlt.deviceledger.service.auth;

import java.util.Set;

/**
 * ClassName: IPermissionService
 * Describe: TODO
 *
 * @Date: 2019/10/7 17:42
 * @Author: 杨开怀
 */
public interface IPermissionService {

    /**
     * 根据账号查询权限
     * @param loginAct
     * @return
     */
    Set<String> getPermissionByLoginAct(String loginAct);
}
