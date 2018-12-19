package com.github.passerr.secretary.controller

import com.github.passerr.secretary.component.SendMessageComponent
import com.github.passerr.secretary.vo.gitlab.GitlabVo
import com.github.passerr.secretary.vo.jira.JiraVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * web钩子rest接口
 * @author xiehai
 * @date 2018/12/12 13:07
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RestController
class WebHooksController {
    @Autowired
    SendMessageComponent sendMessageComponent

    @PostMapping("/jira")
    void jira(@RequestBody JiraVo jiraVo) {
        this.sendMessageComponent.sendJiraMsg(jiraVo)
    }

    @PostMapping("/gitlab")
    void gitlab(@RequestBody GitlabVo gitlabVo) {
        this.sendMessageComponent.sendGitlabMsg(gitlabVo.message())
    }
}
