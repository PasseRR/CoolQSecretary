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
    @ConfigurationProperties(prefix = "secretary.jira.users")
    Map<String, String> jira2qq() {
        return new HashMap<>()
    }

    @Bean
    Map<String, String> qq2Jira() {
        def map = [:]
        this.jira2qq().each { key, value ->
            map.put(value, key)
        }

        return map
    }
}
