package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * {@link SendMessageComponent}
 * @author xiehai
 * @date 2018/12/04 19:02
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class SendMessageComponentSpec extends BaseSpec {
    @Autowired
    SendMessageComponent sendMessageComponent

    def "send message from jira"() {
        when:
        this.sendMessageComponent.sendJiraMsg("hai.xie", "test 测试")
        then:
        notThrown(Exception)
    }

    def "send private message"() {
        when:
        this.sendMessageComponent.sendMsg(new SendAllMessageReq(userId: 304560216, message: "test \ntest\n"))
        then:
        notThrown(Exception)
    }
}
