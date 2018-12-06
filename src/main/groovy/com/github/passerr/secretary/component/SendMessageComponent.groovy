package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * 消息发送组件
 * @author xiehai
 * @date 2018/12/04 16:20
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
@Slf4j
class SendMessageComponent {
    @Autowired
    CoolQApi coolQApi
    @Value("\${secretary.cool.groupId}")
    Long groupId
    @Value("\${secretary.cool.groupType:discuss}")
    String groupType
    @Value("\${secretary.cool.token}")
    String token
    @Autowired
    @Qualifier("group")
    Map<String, String> group
    @Autowired
    Gson gson

    /**
     * 发送jira消息
     * @param key jira帐号
     * @param message 消息内容
     */
    void sendJiraMsg(String key, String message) {
        SendAllMessageReq req = new SendAllMessageReq()
        req.with {
            setId(this.groupType, this.groupId)
            setMessage((this.atUserPrefix(key) ?: "") + message)
            setAutoEscape(false)
        }

        this.sendMsg(req)
    }

    /**
     * 发送gitlab消息通知
     * @param message 消息内容
     */
    void sendGitlabMsg(String message) {
        SendAllMessageReq req = new SendAllMessageReq()
        req.with {
            setId(this.groupType, this.groupId)
            setMessage(message)
            setAutoEscape(true)
        }

        this.sendMsg(req)
    }

    private void sendMsg(SendAllMessageReq req) {
        try {
            def execute = this.coolQApi.sendMsg(this.header(), req).execute()
            log.debug(this.gson.toJson(execute.body()))
        } catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    /**
     * qq @指定用户
     * @param key jira帐号
     * @return CQ码
     */
    private String atUserPrefix(String key) {
        String.format("[CQ:at,qq=%s] ", this.group.get(key))
    }

    /**
     * 酷Q消息发送token
     * @return token
     */
    private String header() {
        String.format("Token %s", this.token)
    }
}
