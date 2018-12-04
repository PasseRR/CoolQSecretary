package com.github.passerr.secretary.component

import com.github.passerr.secretary.Application
import groovy.util.logging.Slf4j
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author xiehai
 * @date 2018/12/04 19:02
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class SendMessageComponentTest {
    @Autowired
    SendMessageComponent sendMessageComponent

    @Test
    void sendJiraMsg(){
        this.sendMessageComponent.sendJiraMsg("hai.xie", "test 测试")
    }
}
