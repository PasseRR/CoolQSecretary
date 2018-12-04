package com.github.passerr.secretary.config

import com.github.passerr.secretary.Application
import groovy.util.logging.Slf4j
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author xiehai
 * @date 2018/12/04 18:45
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class GroupConfigTest {
    @Autowired
    @Qualifier("group")
    Map<String, String> group

    @Test
    void qqMap() {
        log.debug("{}", this.group)
    }
}
