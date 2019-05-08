package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.GitlabApi
import org.springframework.beans.factory.annotation.Autowired
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


    Long getUserQicq(String username) {
        def user = this.gitlabApi.findUsers(username).execute().body()?.get(0)
        // twitter号码存为qq号
        if (user?.twitter?.isNumber()) {
            return user.twitter as long
        }

        return null
    }
}
