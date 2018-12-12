package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.JiraApi
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * jira组件
 * @author xiehai
 * @date 2018/12/10 10:17
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
@Slf4j
@PackageScope
class JiraComponent {
    @Autowired
    JiraApi jiraApi
    @Autowired
    @Qualifier("qq2Jira")
    Map<String, String> qq2Jira
    @Value("\${secretary.jira.token}")
    String token
    @Value("\${secretary.jira.jql.user_issue}")
    String userIssueJql
    @Value("\${secretary.jira.jql.user_task}")
    String userTaskJql
    @Value("\${secretary.jira.jql.user_bug}")
    String userBugJql

    /**
     * 查询用户未完成任务列表
     * @param userId 用户qq
     * @return 任务列表消息
     */
    String userIssue(Long userId) {
        this.jiraApi.search(this.token(), String.format(this.userIssueJql, this.qq2Jira[userId as String]))
            .execute()
            .body()
            .toQqMessage("问题")
    }

    /**
     * 查询用户未完成任务列表
     * @param userId 用户qq
     * @return 任务列表消息
     */
    String userTask(Long userId) {
        this.jiraApi.search(this.token(), String.format(this.userTaskJql, this.qq2Jira[userId as String]))
            .execute()
            .body()
            .toQqMessage("任务")
    }

    /**
     * 查询用户未完成BUG列表
     * @param userId 用户qq
     * @return 任务列表消息
     */
    String userBug(Long userId) {
        this.jiraApi.search(this.token(), String.format(this.userBugJql, this.qq2Jira[userId as String]))
            .execute()
            .body()
            .toQqMessage("BUG")
    }

    /**
     * 查询任务的备注
     * @param key 任务key
     * @return 备注列表信息
     */
    String issueComment(String key) {
        def execute = this.jiraApi.getIssue(this.token(), key)
                          .execute()
        if (execute.code() != 200) {
            return "${key}不存在"
        }

        return this.jiraApi.searchComments(this.token(), key)
                   .execute()
                   .body()
                   .toQqMessage(key)
    }

    /**
     * 任务完成
     * @param userKey 用户key
     * @param issueKey 任务key
     * @return
     */
    String done(String userKey, String issueKey) {
        def execute = this.jiraApi.getIssue(this.token(), issueKey)
                          .execute()
        if (execute.code() != 200) {
            return "${issueKey}不存在"
        }
        def body = execute.body()
        // 经办人校验
        if (body.getFields().getAssigneeKey() != userKey) {
            return "${body?.getFields()?.getIssueType()?.getName() + issueKey}经办人不是你"
        }

        // 已完成校验
        if (body?.getFields()?.isDone()) {
            return "${body?.getFields()?.getIssueType()?.getName() + issueKey}已经完成"
        }

        // 更新任务

        return null
    }

    /**
     * jira api接口token
     * @return token
     */
    private String token() {
        return String.format("Basic %s", this.token)
    }
}
