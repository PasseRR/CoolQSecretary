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

    def "send sync private message"() {
        when:
        this.sendMessageComponent.sendMsg(new SendAllMessageReq(userId: 304560216, message: "test sync message"))
        then:
        notThrown(Exception)
    }

    def "send async private message"() {
        when:
        this.sendMessageComponent.sendMsgAsync(new SendAllMessageReq(userId: 304560216, message: "test async message"))
        then:
        notThrown(Exception)
    }
}
