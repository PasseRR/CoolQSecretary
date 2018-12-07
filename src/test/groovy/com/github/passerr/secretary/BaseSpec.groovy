package com.github.passerr.secretary

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * 单元测试
 * @author xiehai
 * @date 2018/12/05 10:12
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@SpringBootTest(classes = Application, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseSpec extends Specification {
}
