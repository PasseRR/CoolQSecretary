package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * {@link ItpkSettingComponent}
 * @author xiehai
 * @date 2018/12/13 10:33
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class ItpkSettingComponentSpec extends BaseSpec {
    @Autowired
    ItpkSettingComponent itpkSettingComponent

    def "login and set password"() {
        when:
        def resp = this.itpkSettingComponent.login("")
        log.debug(resp)
        then:
        notThrown(Exception)
        !itpkSettingComponent.getCookie()

        when:
        resp = this.itpkSettingComponent.login("123412")
        log.debug(resp)
        then:
        notThrown(Exception)
        !itpkSettingComponent.getCookie()

        when:
        resp = this.itpkSettingComponent.login("1qaz@WSX")
        log.debug(resp)
        then:
        notThrown(Exception)
        itpkSettingComponent.getCookie()
        itpkSettingComponent.getPassword()
    }

    def "robot study"() {
        when:
        def resp = this.itpkSettingComponent.study("你好啊", "啊好你")
        log.debug(resp)
        then:
        notThrown(Exception)

        when:
        resp = this.itpkSettingComponent.login("1qaz@WSX")
        log.debug(resp)
        resp = this.itpkSettingComponent.study("你好啊", "啊好你")
        log.debug(resp)
        then:
        notThrown(Exception)
    }

    def "robot random reply"() {
        when:
        def resp = this.itpkSettingComponent.login("1qaz@WSX")
        log.debug(resp)
        resp = this.itpkSettingComponent.randomReply("我们俩不在同一个频道")
        log.debug(resp)
        then:
        notThrown(Exception)
    }
}
