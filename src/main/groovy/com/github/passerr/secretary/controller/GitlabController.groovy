package com.github.passerr.secretary.controller

import com.github.passerr.secretary.component.SendMessageComponent
import com.github.passerr.secretary.vo.gitlab.GitlabVo
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import java.util.stream.Collectors

/**
 * gitlab webhook
 * @author xiehai
 * @date 2018/12/04 22:12
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RestController
@RequestMapping("/gitlab")
@Slf4j
class GitlabController {
    @Autowired
    Gson gson
    @Autowired
    SendMessageComponent sendMessageComponent

    @PostMapping
    void gitlab(HttpServletRequest request) {
        String json = request.getReader().lines().collect(Collectors.joining("\n"))
        log.debug(json)
        def gitlabVo = this.gson.fromJson(json, GitlabVo.class)
        this.sendMessageComponent.sendGitlabMsg(gitlabVo.message())
    }
}
