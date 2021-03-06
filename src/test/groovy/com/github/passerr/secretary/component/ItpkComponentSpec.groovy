package com.github.passerr.secretary.component

import com.github.passerr.secretary.BaseSpec
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired

/**
 * {@link ItpkComponent}
 * @author xiehai
 * @date 2018/12/04 17:28
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Slf4j
class ItpkComponentSpec extends BaseSpec {
    @Autowired
    ItpkComponent itpkComponent

    def "idiom for dragon"() {
        when:
        def resp = this.itpkComponent.phrase("一往无前")
        log.debug(resp)
        then:
        notThrown(Exception)
        resp != null
    }

    def "joke with itpk"() {
        when:
        def joke = this.itpkComponent.chat("说个笑话")
        log.debug(joke)
        then:
        notThrown(Exception)
        joke != null
    }
}
