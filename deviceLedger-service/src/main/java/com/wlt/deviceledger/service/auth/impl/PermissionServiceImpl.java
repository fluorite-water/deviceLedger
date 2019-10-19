package com.wlt.deviceledger.service.auth.impl;

import com.wlt.deviceledger.service.auth.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * ClassName: PermissionServiceImpl
 * Describe: TODO
 *
 * @Date: 2019/10/7 17:54
 * @Author: 杨开怀
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Override
    public Set<String> getPermissionByLoginAct(String loginAct) {
        return null;
    }

}
