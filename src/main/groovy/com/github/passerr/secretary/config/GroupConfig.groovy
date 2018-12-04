package com.github.passerr.secretary.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * jira用户名和qq号关系
 * @author xiehai
 * @date 2018/12/04 18:43
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Configuration
class GroupConfig {
    @Bean
    @ConfigurationProperties(prefix = "secretary.jira")
    Map<String, String> group() {
        return new HashMap<>()
    }
}
