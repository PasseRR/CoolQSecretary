package com.github.passerr.secretary.controller

import com.github.passerr.secretary.component.SendMessageComponent
import com.github.passerr.secretary.vo.jira.JiraVo
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import java.util.stream.Collectors

/**
 * jira webhook
 * @author xiehai
 * @date 2018/12/04 18:30
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RestController
@RequestMapping("/jira")
@Slf4j
class JiraController {
    @Autowired
    Gson gson
    @Autowired
    SendMessageComponent sendMessageComponent

    @PostMapping
    void jira(HttpServletRequest request) {
        String json = request.getReader().lines().collect(Collectors.joining("\n"))
        log.debug(json)
        def jiraVo = this.gson.fromJson(json, JiraVo)
        this.sendMessageComponent.sendJiraMsg(jiraVo.user(), jiraVo.message())
    }
}
