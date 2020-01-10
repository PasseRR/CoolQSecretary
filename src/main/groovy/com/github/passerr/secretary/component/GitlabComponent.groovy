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
    @Autowired
    @Qualifier("qq2Jira")
    Map<String, String> qq2Jira


    Long getUserQicq(String username) {
        def user = this.gitlabApi.findUsers(username).execute().body()?.get(0)
        // twitter号码存为qq号
        if (user?.twitter?.isNumber()) {
            return user.twitter as long
        }

        return null
    }

    String deploy(String key, Long userId) {
        if (!this.deployMetaMap.containsKey(key)) {
            return "部署任务名不能识别"
        }

        if (!this.qq2Jira.containsKey(String.valueOf(userId))) {
            return "你不是${key}的项目成员"
        }


        DeployMeta meta = this.deployMetaMap.get(key)
        // gitlab帐号
        def account = this.qq2Jira.get(String.valueOf(userId))
        def found = this.gitlabApi.members(meta.projectId).execute().body()?.find {
            it -> it.state == "active" && it.username == account
        }
        if (!found) {
            return "你不是${key}的项目成员"
        }

        def params = [
            token: meta.token,
            ref  : meta.ref
        ]
        params.put("variables[${meta.env}]" as String, "true")
        this.gitlabApi.deploy(meta.projectId, params).execute()

        return ""
    }
}
