package com.github.passerr.secretary.controller

import com.github.passerr.secretary.component.SendMessageComponent
import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import com.github.passerr.secretary.vo.message.MessageVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * 消息发送接口
 * @date 2020/04/16 10:54
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
@RestController
class MessageSenderController {
    @Autowired
    SendMessageComponent sendMessageComponent

    @PostMapping("/send")
    void send(@RequestBody MessageVo messageVo) {
        SendAllMessageReq req = new SendAllMessageReq()
        req.with {
            setId(messageVo.type, messageVo.id)
            setMessage(messageVo.message)
            setAutoEscape(true)
        }

        // 异步发送消息
        this.sendMessageComponent.sendMsgAsync(req)
    }
}
