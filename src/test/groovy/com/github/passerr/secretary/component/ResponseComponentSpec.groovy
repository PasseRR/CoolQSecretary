package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import com.github.passerr.secretary.vo.cool.PrivateMessageReq
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * 命令响应组件单元测试
 * @author xiehai
 * @date 2018/12/27 13:26
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class ResponseComponentSpec extends BaseSpec {
    @Autowired
    ResponseComponent responseComponent

    def "set robot password"() {
        when:
        def message = this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "itpkpwd 1qaz@WSX", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "set question and answer"() {
        when:
        this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "itpkpwd 1qaz@WSX", selfId: 2743046799)
        )
        def message = this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "itpkqa 你是谁?|我是你爸!", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "set random reply"() {
        when:
        this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "itpkpwd 1qaz@WSX", selfId: 2743046799)
        )
        def message = this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "茉莉随机回复 你是谁?|我是你爸!", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "get issue detail"() {
        when:
        def message = this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "detail EDUADMIN-2577", selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }

    def "jira release"() {
        when:
        def message = this.responseComponent.response(
            new PrivateMessageReq(userId: 304560216, message: "release 2019/01/23 16:00 2019/01/23 17:00 阿里云 17:30",
                                  selfId: 2743046799)
        )
        log.debug(message)
        then:
        notThrown(Exception)
    }
}
