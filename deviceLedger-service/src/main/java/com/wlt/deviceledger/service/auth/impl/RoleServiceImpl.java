package com.wlt.deviceledger.service.auth.impl;

import com.wlt.deviceledger.bean.auth.Role;
import com.wlt.deviceledger.service.auth.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * ClassName: RoleServiceImpl
 * Describe: TODO
 *
 * @Date: 2019/10/7 17:55
 * @Author: 杨开怀
 */

@Service
public class RoleServiceImpl implements IRoleService {


    @Override
    public Set<String> getRoleListByLoginAct(String loginAct) {
        return null;
    }

    @Override
    public Role getRoleOfPerm(Integer id) {
        return null;
    }


}
