package com.shrio;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * ClassName: JWTToken
 * Describe: TODO
 *
 * @Date: 2019/10/7 18:35
 * @Author: 杨开怀
 */
public class JWTToken implements AuthenticationToken {


    private String token;
    private String loginPwd;
    private String username;

    /**
     * Instantiates a new Jwt token.
     *
     * @param token the token
     */
    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return loginPwd;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
