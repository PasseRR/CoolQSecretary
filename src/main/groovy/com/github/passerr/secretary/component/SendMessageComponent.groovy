package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.vo.cool.SendDiscussMessageReq
import com.github.passerr.secretary.vo.cool.SendGroupMessageReq
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
        if (this.groupType == "discuss") {
            SendDiscussMessageReq req = new SendDiscussMessageReq()
            req.setDiscussId(this.groupId)
            req.setMessage(this.atUserPrefix(key) + message)
            req.setAutoEscape(false)

            this.sendDiscussMsg(req)
        } else {
            SendGroupMessageReq req = new SendGroupMessageReq()
            req.setGroupId(this.groupId)
            req.setMessage(this.atUserPrefix(key) + message)
            req.setAutoEscape(false)

            this.sendGroupMsg(req)
        }
    }

    /**
     * 发送gitlab消息通知
     * @param message 消息内容
     */
    void sendGitlabMsg(String message) {
        if (this.groupType == "discuss") {
            SendDiscussMessageReq req = new SendDiscussMessageReq()
            req.setDiscussId(this.groupId)
            req.setMessage(message)
            req.setAutoEscape(true)

            this.sendDiscussMsg(req)
        } else {
            SendGroupMessageReq req = new SendGroupMessageReq()
            req.setGroupId(this.groupId)
            req.setMessage(message)
            req.setAutoEscape(true)

            this.sendGroupMsg(req)
        }
    }

    private void sendGroupMsg(SendGroupMessageReq req) {
        try {
            log.debug(this.gson.toJson(req))
            def execute = this.coolQApi.sendGroupMsg(this.header(), req).execute()
            log.debug(this.gson.toJson(execute.body()))
        } catch (Exception e) {
            log.debug(e.getMessage(), e)
        }
    }

    private void sendDiscussMsg(SendDiscussMessageReq req) {
        try {
            def execute = this.coolQApi.sendDiscussMsg(this.header(), req).execute()
            log.debug(this.gson.toJson(execute.body()))
        } catch (Exception e) {
            log.debug(e.getMessage(), e)
        }
    }

    private String atUserPrefix(String key) {
        return String.format("[CQ:at,qq=%s] ", this.group.get(key))
    }

    private String header() {
        return String.format("Token %s", this.token)
    }
}
