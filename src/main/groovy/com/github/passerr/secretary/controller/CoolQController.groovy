package com.github.passerr.secretary.controller

import com.github.passerr.secretary.vo.cool.TypeVo
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import java.util.stream.Collectors

/**
 * 酷Q消息接收接口
 * @author xiehai
 * @date 2018/12/04 15:33
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@RestController
@RequestMapping("/coolQ")
@Slf4j
class CoolQController {
    @Autowired
    Gson gson

    @PostMapping
    def receive(HttpServletRequest request) {
        String json = request.getReader().lines().collect(Collectors.joining("\n"))
        log.debug(json)
        def type = this.gson.fromJson(json, TypeVo.class)
        log.debug(type.toString())
        switch (type.getPostType()) {
            case "message":
                // 只处理私聊/群消息/讨论组消息
                switch (type.getMessageType()) {
                    case "private":
                        break
                    case "group":
                        break
                    case "discuss":
                        break
                    default:
                        log.warn("暂时不支持的消息类型{}", type.getMessageType())
                        break
                }
                break
            default:
                log.warn("暂时不支持的推送消息类型{}", type.getPostType())
                break
        }
    }
}
