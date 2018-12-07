package com.github.passerr.secretary.config

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

/**
 * {@link GroupConfig}单元测试
 * @author xiehai
 * @date 2018/12/04 18:45
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class GroupConfigSpec extends BaseSpec {
    @Autowired
    @Qualifier("jira2qq")
    Map<String, String> jira2qq
    @Autowired
    @Qualifier("qq2Jira")
    Map<String, String> qq2Jira

    def "test jira2qq non null"() {
        log.debug("{}", this.jira2qq)

        expect:
        this.jira2qq.size() > 0
    }

    def "test qq2Jira non null"() {
        log.debug("{}", this.qq2Jira)

        expect:
        this.qq2Jira.size() > 0
    }
}
