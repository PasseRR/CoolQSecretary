package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j

/**
 * {@link JiraConfigUtil}
 * @author xiehai
 * @date 2018/12/11 10:20
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class JiraConfigUtilSpec extends BaseSpec {
    def "bug issue types"(){
        when:
        def typeIds = JiraConfigUtil.getBugTypeIds()
        log.debug("{}", typeIds)
        then:
        notThrown(Exception)
        typeIds != null
    }

    def "done transitions"(){
        when:
        def doneTransitions = JiraConfigUtil.getDoneTransitions()
        log.debug("{}", doneTransitions)
        then:
        notThrown(Exception)
        doneTransitions != null
    }
}
