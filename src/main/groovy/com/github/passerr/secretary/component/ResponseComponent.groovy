package com.github.passerr.secretary.component

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 消息对话组件
 * @author xiehai
 * @date 2018/12/27 13:21
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
class ResponseComponent {
    @Autowired
    ItpkComponent itpkComponent
    @Autowired
    List<Command<?>> commands

    /**
     * 应答对话消息
     * @param req 对话内容
     * @return 应答对话
     */
    String response(MessageReq req) {
        String message = req.getLegalMessage()
        // 命令匹配
        Command<?> command = this.commands.stream()
                                 .filter({ it -> it.match(message) })
                                 .findFirst()
                                 .orElse(null)
        // 根据命令应答
        command ? command.execute(req) : this.itpkComponent.chat(message)
    }
}
