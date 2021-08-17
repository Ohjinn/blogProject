package com.cos.blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class Enctest {
    @Test
    public void 해쉬_암호화(){
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234해쉬 : " + encPassword);
    }
}