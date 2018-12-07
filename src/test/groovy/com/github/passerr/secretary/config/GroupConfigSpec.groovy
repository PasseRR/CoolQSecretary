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
    @Qualifier("group")
    Map<String, String> group

    def "test qqMap non null"() {
        log.debug("{}", this.group)

        expect:
        this.group.size() > 0
    }
}
