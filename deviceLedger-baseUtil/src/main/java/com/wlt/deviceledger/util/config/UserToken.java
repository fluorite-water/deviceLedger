package com.wlt.deviceledger.util.config;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: RememberMeAuthenticationToken
 * Describe: TODO
 *
 * @Date: 2019/10/19 18:58
 * @Author: 杨开怀
 */

public class UserToken extends UsernamePasswordToken {

    private String username;
    private String loginPwd;
    private boolean rememberMe;
    private String host;
    private List<Map<String, Object>> roleList;
    private String token;
    private List<String> perList;


    public UserToken(String username, String password, String token, List<Map<String, Object>> roleList) {
        super(username, password, false, (String)null);
        this.token = token;
        this.roleList = roleList;
        this.loginPwd = password;
    }

    public UserToken(String username, String password, String token) {
        super(username, password, false, (String)null);
        this.token = token;
        this.roleList = new ArrayList<>();
        this.loginPwd = password;
    }

    public void setRoleList(List roleList) {
        this.roleList = roleList;
    }

    public List<String> getPerList() {
        return perList;
    }

    public void setPerList(List<String> perList) {
        this.perList = perList;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }


    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    public List getRoleList() {
        return roleList;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
