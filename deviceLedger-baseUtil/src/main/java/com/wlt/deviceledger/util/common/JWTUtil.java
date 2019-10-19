package com.wlt.deviceledger.util.common;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
/**
 * ClassName: JWTUtil
 * Describe: TODO
 *
 * @Date: 2019/10/7 18:08
 * @Author: 杨开怀
 */
public class JWTUtil {
    /**
     * 过期时间 24 小时
     */
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    /**
     * 密钥，注意这里如果真实用到，应当设置到复杂点，相当于私钥的存在。如果被人拿到，想到于它可以自己制造token了。
     */
    private static final String SECRET = "LIAODASHUAI";

    /**
     * 生成 token, 5min后过期
     *
     * @param username 用户名
     * @return 加密的token string
     */
    public static String createToken(String username) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确 boolean
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @param token the token
     * @return token中包含的用户名 username
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
