package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.JiraApi
import com.github.passerr.secretary.vo.jira.IssueVo
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
        this.jiraApi.search(String.format(this.userIssueJql, this.qq2Jira[userId as String]))
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
        this.jiraApi.search(String.format(this.userTaskJql, this.qq2Jira[userId as String]))
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
        this.jiraApi.search(String.format(this.userBugJql, this.qq2Jira[userId as String]))
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
        def issue = this.issue(key)
        if (issue instanceof String) {
            return issue
        }

        return this.jiraApi.searchComments(key)
                   .execute()
                   .body()
                   .toQqMessage(key)
    }

    /**
     * 获得任务明细
     * @param key
     * @return
     */
    def issue(String key) {
        def execute = this.jiraApi.getIssue(key)
                          .execute()
        if (execute.code() != 200) {
            return "${key}不存在" as String
        }

        execute.body()
    }

    /**
     * 问题明细
     * @param key 任务编号
     * @return 问题明细消息
     */
    String issueDetail(String key) {
        def issue = this.issue(key)
        issue instanceof String ?: (issue as IssueVo).toDetailQqMessage()
    }

    /**
     * 任务完成
     * @param userKey 用户key
     * @param issueKey 任务key
     * @return
     */
    String done(String userKey, String issueKey) {
        def issue = this.issue(issueKey)
        if (issue instanceof String) {
            return issue
        }
        def body = issue as IssueVo
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
}
