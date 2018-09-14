package com.springboot;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    @Test
    public void contextLoads() {
        String hashAlgorithmName = "MD5";
        String credentials = "0000";
        int hashIterations = 1024;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, "lucia", hashIterations);
        System.out.println(obj);
    }

}
