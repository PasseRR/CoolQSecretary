package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author xiehai
 * @date 2018/12/27 17:03
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class BaiduComponentSpec extends BaseSpec {
    @Autowired
    BaiduComponent baiduComponent

    def "translate zh to en"() {
        when:
        def translate = this.baiduComponent.translate("en", "维护")
        log.debug(translate)
        then:
        notThrown(Exception)
    }
}
