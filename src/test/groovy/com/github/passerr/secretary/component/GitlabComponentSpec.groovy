package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * {@link GitlabComponent}单元测试
 * @Copyright (c)tellyes tech. inc. co.,ltd
 * @date 2019/05/08 10:29
 * @author xiehai
 */
@Slf4j
class GitlabComponentSpec extends BaseSpec {
    @Autowired
    GitlabComponent gitlabComponent

    def "get user qq"() {
        when:
        def qq = this.gitlabComponent.getUserQicq("yong.li")
        log.debug("qq=${qq}")
        then:
        true
    }
}
