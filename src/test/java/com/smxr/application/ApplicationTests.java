package com.smxr.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    }

}
