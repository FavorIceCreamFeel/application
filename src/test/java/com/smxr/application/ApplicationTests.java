package com.smxr.application;

import com.smxr.application.utils.ApplicationUtilsOne;
import com.smxr.application.utils.jwtUtils.JwtConfigUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String pwd="123456";
        System.out.println("原密码："+pwd);
        String pwdEncode = passwordEncoder.encode(pwd);
        System.out.println("encode处理后："+pwdEncode);
        boolean matches = passwordEncoder.matches(pwd, "$2b$15$EE1gm.seDTBYdBHn51f6UOzMh74UArqQ4BDLNwHmM0c6h7wQpcNEK");
        System.out.println("对比："+matches);
        boolean b = passwordEncoder.upgradeEncoding(pwdEncode);
        System.out.println("是否需要再次编号："+b);

//        System.out.println("时间1："+new Date());
//        System.out.println("时间1："+new Date(System.currentTimeMillis()));
//        String jwt="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJyb2xlIjoiYWRtaW4iLCJ1c2VybmFtZSI6IjIxMyJ9.pMZ9d-QNFEf8049gTCJ_uhHemo_yFgCYT-ds_iSE9HDOf59ZpboP_cPZ1M0X2og2jUCoo5HE-jNUvcMP-6voXQ";
//        String jwtToken = JwtConfigUtils.createJwtToken();
        String jwtToken ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzbXhyX3VzZXIiLCJhdWQiOiIxNzUxMzIzNDU4MSIsInJvbGUiOlt7ImF1dGhvcml0eSI6IlJvbGUifSx7ImF1dGhvcml0eSI6IlJvbGUxIn0seyJhdXRob3JpdHkiOiJSb2xlMiJ9LHsiYXV0aG9yaXR5IjoiUm9sZTMifSx7ImF1dGhvcml0eSI6IlJvbGU0In0seyJhdXRob3JpdHkiOiJSb2xlNSJ9LHsiYXV0aG9yaXR5IjoiU1NSIn0seyJhdXRob3JpdHkiOiJTU1MifV0sIm5iZiI6MTU4NDgwMTM1NSwiaXNzIjoic214ciIsImV4cCI6MTU4NDgwNDk1NSwiaWF0IjoxNTg0ODAxMzU1LCJqdGkiOiIxNzUxMzIzNDU4MSIsInVzZXJuYW1lIjoiMTc1MTMyMzQ1ODEifQ.JZG519cTbAjTnLJX3C9c0KHdbpmc0Vdw7c5xwikzS2BYrTN6VNMAkQhQP5ok8k_o5PQi1T0QgyEnnRvKMunwyw";
        System.out.println("chuangjian token:"+jwtToken);
        System.out.println("-----------------------------");
        Claims claims = null;
        try {
            claims = JwtConfigUtils.getClaim(jwtToken);
        } catch (Exception e) {
            System.out.println("claims是空");
            e.printStackTrace();
        }
        System.out.println("1111111111111111111111111111111111111");
        System.out.println("：" + claims.getId());
        System.out.println(":" + claims.getSubject());
        System.out.println("：" + ApplicationUtilsOne.getDateTime(claims.getIssuedAt()));
        System.out.println(":：" +ApplicationUtilsOne.getDateTime(claims.getExpiration()));
        System.out.println(":：" + claims.getAudience());
        System.out.println(":：" + claims.getIssuer());
        System.out.println(":：" + ApplicationUtilsOne.getDateTime(claims.getNotBefore()));


//header={typ=JWT, alg=HS512},body={role=admin, username=213},signature=pMZ9d-QNFEf8049gTCJ_uhHemo_yFgCYT-ds_iSE9HDOf59ZpboP_cPZ1M0X2og2jUCoo5HE-jNUvcMP-6voXQ
//        jwt1.getBody()
    }

}
