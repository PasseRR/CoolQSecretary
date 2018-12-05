package com.github.passerr.secretary.config

import com.github.passerr.secretary.BaseTest
import groovy.util.logging.Slf4j
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

/**
 * {@link GroupConfig}单元测试
 * @author xiehai
 * @date 2018/12/04 18:45
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class GroupConfigTest extends BaseTest {
    @Autowired
    @Qualifier("group")
    Map<String, String> group

    @Test
    void qqMap() {
        log.debug("{}", this.group)
    }
}
