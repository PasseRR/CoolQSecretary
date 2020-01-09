package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.GitlabApi
import com.github.passerr.secretary.vo.gitlab.DeployMeta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

/**
 * gitlab组件
 * @author xiehai* @date 2019/05/08 10:20
 * @Copyright (c)tellyes tech. inc. co.,ltd
 */
@Component
class GitlabComponent {
    @Autowired
    GitlabApi gitlabApi
    @Autowired
    @Qualifier("deployMetaMap")
    Map<String, DeployMeta> deployMetaMap


    Long getUserQicq(String username) {
        def user = this.gitlabApi.findUsers(username).execute().body()?.get(0)
        // twitter号码存为qq号
        if (user?.twitter?.isNumber()) {
            return user.twitter as long
        }

        return null
    }

    String deploy(String key) {
        if (!this.deployMetaMap.containsKey(key)) {
            return "部署任务名不能识别"
        }

        DeployMeta meta = this.deployMetaMap.get(key)
        def params = [
            token: meta.token,
            ref  : meta.ref
        ]
        params.put("variables[${meta.env}]" as String, "true")
        this.gitlabApi.deploy(meta.projectId, params).execute()

        return ""
    }
}
