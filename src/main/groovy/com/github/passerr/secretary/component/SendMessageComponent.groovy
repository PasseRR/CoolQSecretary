package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import com.github.passerr.secretary.vo.jira.JiraVo
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
    @Autowired
    @Qualifier("jira2qq")
    Map<String, String> jira2qq
    @Autowired
    Gson gson

    /**
     * 发送jira消息
     * @param key jira帐号
     * @param message 消息内容
     */
    void sendJiraMsg(JiraVo jiraVo) {
        SendAllMessageReq req = new SendAllMessageReq()
        req.with {
            setId(this.groupType, this.groupId)
            // 若jira对应帐号qq不存在 不加@直接通知
            setMessage(
                Optional.ofNullable(this.jira2qq[jiraVo?.user()?.getKey()])
                        .map({ s -> atUserPrefix(s) })
                        .orElse("【${jiraVo?.user()?.getDisplayName()}】 ") + jiraVo.message()
            )
            setAutoEscape(false)
        }

        this.sendMsgAsync(req)
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

        this.sendMsgAsync(req)
    }

    /**
     * 消息直接发送
     * @param req 报文内容
     */
    protected void sendMsg(SendAllMessageReq req) {
        try {
            def execute = this.coolQApi.sendMsg(req).execute()
            log.debug(this.gson.toJson(execute.body()))
        } catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    /**
     * 异步消息发送
     * @param req 报文内容
     */
    protected void sendMsgAsync(SendAllMessageReq req) {
        try {
            def execute = this.coolQApi.sendMsgAsync(req).execute()
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
    private static String atUserPrefix(String qq) {
        String.format("[CQ:at,qq=%s] ", qq)
    }
}
