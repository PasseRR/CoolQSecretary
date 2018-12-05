package com.github.passerr.secretary

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * 单元测试
 * @author xiehai
 * @date 2018/12/05 10:12
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseTest {
}
