package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.vo.cool.SendAllMessageReq
import com.github.passerr.secretary.vo.jira.IssueVo
import com.github.passerr.secretary.vo.jira.JiraVo
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component 
/**
 * 消息发送组件
 * @author xiehai* @date 2018/12/04 16:20
 * @Copyright (c) tellyes tech. inc. co.,ltd
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
    @Autowired
    GitlabComponent gitlabComponent

    /**
     * 发送jira消息
     * @param key jira帐号
     * @param message 消息内容
     */
    void sendJiraMsg(JiraVo jiraVo) {
        // 若存在jira帐号与qq映射关系 则通知
        Optional.ofNullable(this.jira2qq[jiraVo?.user()?.getKey()])
            .map({ s -> atUserPrefix(s) })
            .ifPresent({ msg ->
                SendAllMessageReq req = new SendAllMessageReq()
                req.with {
                    setId(this.groupType, this.groupId)
                    setMessage(msg + jiraVo.message())
                    setAutoEscape(false)
                }
                this.sendMsgAsync(req)
            })
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
     * gitlab通知消息
     * @param message 消息内容
     * @param username gitlab用户名
     */
    void sendPrivateGitlabMessage(String message, String username) {
        def qq = this.gitlabComponent.getUserQicq(username)
        if (qq) {
            SendAllMessageReq req = new SendAllMessageReq()
            req.with {
                setId("", qq)
                setMessage(message)
                setAutoEscape(true)
            }

            this.sendMsgAsync(req)
        }
    }

    /**
     * 发送jira问题发布
     * @param issueVos 问题列表
     * @param releaseTime 发布时间
     * @param env 发布环境
     */
    void sendJiraReleaseMsg(List<IssueVo> issueVos, String releaseTime, String env) {
        SendAllMessageReq req = new SendAllMessageReq()

        // 解析问题列表
        StringBuilder issue = new StringBuilder()
        Set<String> users = new HashSet<>()
        issueVos.eachWithIndex { IssueVo entry, int i ->
            // 存储需要@的人
            def s = this.jira2qq[entry.fields.getCreatorKey()]
            if (s) {
                users.add(s)
            }
            issue.append("${i + 1}. 【${entry.key}】 ${entry.fields.summary}\n")
        }
        StringBuilder title = new StringBuilder()
        users.each {
            title.append("${atUserPrefix(it)}")
        }
        title.append("${releaseTime}会进行${env}环境更新，解决问题如下：\n")
        title.append(issue)

        req.with {
            setId(this.groupType, this.groupId)
            setMessage(title.substring(0, title.length() - 1))
            setAutoEscape(false)
        }

        this.sendMsgAsync(req)
    }

    /**
     * 消息直接发送
     * @param req 报文内容
     */
    long sendMsg(SendAllMessageReq req) {
        try {
            def execute = this.coolQApi.sendMsg(req).execute()
            def body = execute.body()
            log.debug(this.gson.toJson(body))
            return body.data.messageId
        } catch (Exception e) {
            log.error(e.getMessage(), e)
        }
    }

    /**
     * 异步消息发送
     * @param req 报文内容
     */
    void sendMsgAsync(SendAllMessageReq req) {
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
