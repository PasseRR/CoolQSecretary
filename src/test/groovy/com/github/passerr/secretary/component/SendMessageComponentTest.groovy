package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseTest
import groovy.util.logging.Slf4j
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * {@link SendMessageComponent}
 * @author xiehai
 * @date 2018/12/04 19:02
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class SendMessageComponentTest extends BaseTest {
    @Autowired
    SendMessageComponent sendMessageComponent

    @Test
    void sendJiraMsg() {
        this.sendMessageComponent.sendJiraMsg("hai.xie", "test 测试")
    }
}
