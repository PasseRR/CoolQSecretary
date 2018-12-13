package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import com.github.passerr.secretary.constants.HelpDoc
import com.github.passerr.secretary.vo.cool.PrivateMessageReq
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
        this.sendMessageComponent.sendMsg(new SendAllMessageReq(userId: 304560216, message: HelpDoc.DOC))
        then:
        notThrown(Exception)
    }

    def "set robot password"() {
        when:
        def message = this.sendMessageComponent.responseMessage(
            new PrivateMessageReq(userId: 304560216, message: "itpkpwd 1qaz@WSX", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "set question and answer"() {
        when:
        this.sendMessageComponent.responseMessage(
            new PrivateMessageReq(userId: 304560216, message: "itpkpwd 1qaz@WSX", selfId: 2743046799)
        )
        def message = this.sendMessageComponent.responseMessage(
            new PrivateMessageReq(userId: 304560216, message: "itpkqa 你是谁?|我是你爸!", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "set random reply"() {
        when:
        this.sendMessageComponent.responseMessage(
            new PrivateMessageReq(userId: 304560216, message: "itpkpwd 1qaz@WSX", selfId: 2743046799)
        )
        def message = this.sendMessageComponent.responseMessage(
            new PrivateMessageReq(userId: 304560216, message: "茉莉随机回复 你是谁?|我是你爸!", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "get issue detail"() {
        when:
        def message = this.sendMessageComponent.responseMessage(
            new PrivateMessageReq(userId: 304560216, message: "detail EDUADMIN-2577", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }
}
