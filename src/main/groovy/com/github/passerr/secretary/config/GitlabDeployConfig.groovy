package com.github.passerr.secretary.config

import com.github.passerr.secretary.vo.gitlab.DeployMeta
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * gitlab部署配置
 * @date 2020/01/09 16:14
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
@Configuration
class GitlabDeployConfig {
    @Bean
    @ConfigurationProperties(prefix = "secretary.gitlab.deploy")
    List<DeployMeta> deployMetas() {
        return new ArrayList<>()
    }

    @Bean
    Map<String, DeployMeta> deployMetaMap(List<DeployMeta> deployMetas) {
        def map = [:]
        deployMetas.each { it -> map.put(it.key, it) }
        return map
    }
}
