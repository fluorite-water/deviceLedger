package com.wlt.deviceledger.settings.shrio;

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
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
