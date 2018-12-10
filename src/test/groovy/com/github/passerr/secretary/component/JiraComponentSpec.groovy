package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * jira接口测试
 * @author xiehai
 * @date 2018/12/10 15:35
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class JiraComponentSpec extends BaseSpec {
    @Autowired
    JiraComponent jiraComponent

    def "user task list"() {
        when:
        def task = this.jiraComponent.userTask(304560216)
        log.debug(task)
        then:
        notThrown(Exception)
        task != null
    }

    def "user bug list"() {
        when:
        def bug = this.jiraComponent.userBug(304560216)
        log.debug(bug)
        then:
        notThrown(Exception)
        bug != null
    }
}
