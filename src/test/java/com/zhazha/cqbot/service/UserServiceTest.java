package com.zhazha.cqbot.service;

import com.zhazha.cqbot.bean.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {
    
    @BeforeEach
    void setUp() {
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Resource
    private UserService userService;
    
    public static void main(String[] args) throws Exception {
        Long l = null;
        System.err.println(l.toString());
    }
    
    @Test
    void getBlockUser() {
        User user = userService.getUser((Long) null);
        System.err.println(user);
    }
    
    @Test
    void getUser() {
        User user = userService.getUser(2033445917L);
        System.err.println(user);
    }
    
    @Test
    void testGetUser() {
        User user = userService.getUser("2033445917");
        System.err.println(user);
    }
}