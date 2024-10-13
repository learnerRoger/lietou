package com.lietou.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * Jwt工具类
 *
 * @Author : lietou
 * @Date : 2023/1/30
 */
public class JWTUtil {

    /**
     * 生成token
     * @param id
     * @param password
     * @return
     */
    public static String generateToken(Integer id,String password) {
        String sign = JWT.create().withAudience(id.toString()) // 设置载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 1)) // 设置签名过期的时间
                .sign(Algorithm.HMAC256(password));
        System.out.println("jwt生成的token为： " + sign);
        return sign;
    }


}
