package com.github.passerr.secretary.component

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.GroupMessageReq
import com.github.passerr.secretary.vo.cool.MessageReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 消息对话组件
 * @author xiehai* @date 2018/12/27 13:21
 * @Copyright (c) tellyes tech. inc. co.,ltd
 */
@Component
class ResponseComponent {
    @Autowired
    ItpkComponent itpkComponent
    @Autowired
    MatchComponent matchComponent
    @Autowired
    List<Command<?>> commands

    /**
     * 应答对话消息
     * @param req 对话内容
     * @return 应答对话
     */
    String response(MessageReq req) {
        this.commands.stream()
            .filter({ it -> it.match(req.getLegalMessage()) })
            .findFirst()
        // 若命令匹配按照命令响应
            .map({ it -> it.execute(req) })
        // 未匹配到命令 按照机器人对话响应
            .orElseGet({ this.itpkComponent.chat(req.getLegalMessage()) })
    }

    /**
     * 回复 抄送源@消息
     * @param req {@link MessageReq}
     * @return 回复字符串
     */
    String reply(MessageReq req) {
        if (req.needReply()) {
            return String.format("[CQ:reply,id=%d][CQ:at,qq=%d]%s", req.messageId, req.userId, this.response(req))
        }
    }

    /**
     * 群消息回复
     * @param req {@link GroupMessageReq}
     * @return 消息回复
     */
    String replyGroup(GroupMessageReq req) {
        // 答题支持
        if (this.matchComponent.isInQuiz(req.groupId)) {
            String message = (req.needReply() ? req.getLegalMessage() : req.getMessage()).toUpperCase()
            this.matchComponent.answer(req.groupId, message, req.userId)
            return null
        }

        return this.reply(req)
    }
}
