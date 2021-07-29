package com.github.passerr.secretary.controller

import com.github.passerr.secretary.component.SendMessageComponent
import com.github.passerr.secretary.vo.gitlab.GitlabVo
import com.github.passerr.secretary.vo.jira.JiraVo
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import java.util.stream.Collectors

/**
 * web钩子rest接口
 * @author xiehai* @date 2018/12/12 13:07
 * @Copyright (c) tellyes tech. inc. co.,ltd
 */
@RestController
class WebHooksController {
    @Autowired
    SendMessageComponent sendMessageComponent
    @Autowired
    Gson gson

    @PostMapping("/jira")
    void jira(HttpServletRequest request) {
        String json = request.getReader().lines().collect(Collectors.joining("\n"))
        def jiraVo = this.gson.fromJson(json, JiraVo)
        // 没有或者不支持的事件名
        if (!jiraVo.getIssueEventTypeName()) {
            return
        }

        // 问题明细存在
        if (jiraVo.getIssue()) {
            this.sendMessageComponent.sendJiraMsg(jiraVo)
        }
    }

    @PostMapping("/gitlab")
    void gitlab(@RequestBody GitlabVo gitlabVo) {
        this.sendMessageComponent.sendGitlabMsg(gitlabVo.message())
        if (gitlabVo.isPipeline()) {
            // 异步发送私人通知消息
            this.sendMessageComponent.sendPrivateGitlabMessage(gitlabVo.message(), gitlabVo.user.username)
        }
    }
}
