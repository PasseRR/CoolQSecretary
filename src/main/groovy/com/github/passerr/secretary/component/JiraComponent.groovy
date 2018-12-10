package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.JiraApi
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * jira组件
 * @author xiehai
 * @date 2018/12/10 10:17
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
@Slf4j
@PackageScope
class JiraComponent {
    @Autowired
    JiraApi jiraApi
}
