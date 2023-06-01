package com.zhazha.cqbot.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PermissionUtilsTest {
    
    @BeforeEach
    void setUp() {
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Test
    void isBlockUser() {
    }
    
    @Test
    void hasPermission() {
        System.err.println(PermissionUtils.hasPermission("ADMIN", "ADMIN"));
        System.err.println(PermissionUtils.hasPermission("ADMIN", "USER"));
        System.err.println(PermissionUtils.hasPermission("ADMIN", "BLOCK"));
        System.err.println(PermissionUtils.hasPermission("USER", "ADMIN"));
        System.err.println(PermissionUtils.hasPermission("USER", "USER"));
        System.err.println(PermissionUtils.hasPermission("USER", "BLOCK"));
        System.err.println(PermissionUtils.hasPermission("BLOCK", "ADMIN"));
        System.err.println(PermissionUtils.hasPermission("BLOCK", "USER"));
        System.err.println(PermissionUtils.hasPermission("BLOCK", "BLOCK"));
    }
    
    @Test
    void getPermissionLevel() {
    }
}