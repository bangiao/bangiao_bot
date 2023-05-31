package com.zhazha.cqbot.remote.msg;

import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.remote.msg.result.GetMsgResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class RMessageServiceTest {
    
    @Resource
    private RMessageService rMessageService;
    
    @BeforeEach
    void setUp() {
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Test
    void sendMessage() {
        rMessageService.sendMessage(2033445917L, "hehe", false);
    }
    
    @Test
    void getMsg() {
        GetMsgResult msg = rMessageService.getMsg(-1320528546);
        System.err.println(msg);
    }
    
    @Test
    void deleteMsg() {
    }
    
    @Test
    void getForwardMsg() {
    }
    
    @Test
    void testSendMessage() {
        rMessageService.sendMessage(Long.valueOf(Constants.adminQQ),
                "Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25049820]", true);
    }
}