package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseTest
import groovy.util.logging.Slf4j
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * {@link ItpkComponent}
 * @author xiehai
 * @date 2018/12/04 17:28
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class ItpkComponentTest extends BaseTest {
    @Autowired
    ItpkComponent itpkComponent

    @Test
    void message() {
        log.debug(this.itpkComponent.message("@cy1往无前"))
    }

    @Test
    void bytes() {
        log.debug("1往无前".getBytes().length as String)
        log.debug("一往无前".getBytes().length as String)
    }

    @Test
    void joke() {
        log.debug(this.itpkComponent.message("笑话"))
    }
}
