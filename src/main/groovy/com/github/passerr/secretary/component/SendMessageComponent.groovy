package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.vo.cool.MessageReq
import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import static com.github.passerr.secretary.enums.RobotCommand.*

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
    @Autowired
    ItpkComponent itpkComponent
    @Autowired
    ItpkSettingComponent itpkSettingComponent
    @Autowired
    JiraComponent jiraComponent
    @Value("\${secretary.cool.groupId}")
    Long groupId
    @Value("\${secretary.cool.groupType:discuss}")
    String groupType
    @Value("\${secretary.cool.token}")
    String token
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
    void sendJiraMsg(String key, String message) {
        SendAllMessageReq req = new SendAllMessageReq()
        req.with {
            setId(this.groupType, this.groupId)
            setMessage((this.atUserPrefix(key) ?: "") + message)
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
     * 应答对话消息
     * @param req 对话内容
     * @return 应答对话
     */
    String responseMessage(MessageReq req) {
        def message = req.getLegalMessage()
        switch (message) {
        // 查询jira用户未完成的问题列表
            case ["我的问题", "issue"]:
                return this.jiraComponent.userIssue(req.getUserId())
        // 查询jira用户未完成的任务列表
            case ["我的任务", "task"]:
                return this.jiraComponent.userTask(req.getUserId())
        // 查询jira用户未完成bug
            case ["我的缺陷", "bug"]:
                return this.jiraComponent.userBug(req.getUserId())
        // 查询备注
            case ~/^${JIRA_REMARK_CN.getCommand()} \w+-\d+$/:
                return this.jiraComponent.issueComment(JIRA_REMARK_CN.getOption(message))
            case ~/^${JIRA_REMARK_EN.getCommand()} \w+-\d+$/:
                return this.jiraComponent.issueComment(JIRA_REMARK_EN.getOption(message))
        // 完成任务/bug
            case ~/^${JIRA_DONE_CN.getCommand()} \w+-\d+$/:
            case ~/^${JIRA_DONE_EN.getCommand()} \w+-\d+$/:
                return "功能开发中..."
        // itpk密码设置获取cookie
            case ~/^${ITPK_PWD_CN.getCommand()} ([\s\S]+)$/:
                return this.itpkSettingComponent.login(ITPK_PWD_CN.getOption(message))
            case ~/^${ITPK_PWD_EN.getCommand()} ([\s\S]+)$/:
                return this.itpkSettingComponent.login(ITPK_PWD_EN.getOption(message))
        // 学习问答
            case ~/^${ITPK_QA_CN.getCommand()} ([\s\S]+)\|([\s\S]+)$/:
                return this.itpkSettingComponent.study(ITPK_QA_CN.getOption(message))
            case ~/^${ITPK_QA_EN.getCommand()} ([\s\S]+)\|([\s\S]+)$/:
                return this.itpkSettingComponent.study(ITPK_QA_EN.getOption(message))
        // 成语接龙
            case { message.length() == 4 }:
                return this.itpkComponent.phrase(message)
        // 普通对话
            default:
                return this.itpkComponent.chat(message)
        }
    }

    /**
     * 消息直接发送
     * @param req 报文内容
     */
    protected void sendMsg(SendAllMessageReq req) {
        try {
            def execute = this.coolQApi.sendMsg(this.header(), req).execute()
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
            def execute = this.coolQApi.sendMsgAsync(this.header(), req).execute()
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
        String.format("[CQ:at,qq=%s] ", this.jira2qq[key])
    }

    /**
     * 酷Q消息发送token
     * @return token
     */
    private String header() {
        String.format("Token %s", this.token)
    }
}
