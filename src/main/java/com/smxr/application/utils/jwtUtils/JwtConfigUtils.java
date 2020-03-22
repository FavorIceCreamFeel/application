package com.smxr.application.utils.jwtUtils;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smxr
 * @date 2020/3/11
 * @time 18:00
 * JWT配置工具类
 */
@Log
public class JwtConfigUtils {
    /**
     * 签名秘钥
     */
    public static final String SECRET ="smxr";

    public static String createJwtToken() {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

//        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claimsMap = new HashMap();
        claimsMap.put("username", authentication.getName());
        System.out.println(authentication.getAuthorities().toString());
        claimsMap.put("role", authentication.getAuthorities());
        //时间设置
        long timeMillis = System.currentTimeMillis();

        // Let's set the JWT Claims
        String compact = Jwts.builder()
                .setClaims(claimsMap)
                .setId(authentication.getName())
                .setIssuer("smxr")
                .setSubject("smxr_user")
                .setAudience(authentication.getName())
                .setIssuedAt(new Date(timeMillis))//颁发时间
                .setNotBefore(new Date(timeMillis))//生效时间
                .setExpiration(new Date(timeMillis + 60 * 60 * 1000))//过期时间
//                .compressWith(CompressionCodecs.DEFLATE)//签名压缩方法
                .signWith(SignatureAlgorithm.HS512, signingKey).compact();
        log.info("生成token："+compact);
        return compact;

    }
//  这里不能进行try/catch处理要不然会无法拿到时间等数据
    public static Claims getClaim(String jwt)throws Exception {
            Claims claims = Jwts.parser()
                    .setSigningKey(new SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET),SignatureAlgorithm.HS512.getJcaName()))
                    .parseClaimsJws(jwt).getBody();
            log.info("拿到Claims："+claims.toString());
            return claims;
    }
//    获取Jwt可以拿到
    public static Jwt getJwt(String jwt){
        Jwt jwt1 = Jwts.parser().setSigningKey(SECRET).parse(jwt);
        log.info("JWT:"+jwt1);
        return jwt1;
    }

    /**
     * 验证jwt的有效期
     * @param claims
     * @return
     */
    public static Boolean isTokenExpired(Claims claims) {
        long endTime = claims.getExpiration().getTime();
        long startTime = claims.getNotBefore().getTime();
        long newTime = System.currentTimeMillis();
        return startTime<=newTime&&newTime<=endTime;
    }
}
