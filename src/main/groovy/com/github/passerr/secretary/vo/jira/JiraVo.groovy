package com.github.passerr.secretary.vo.jira

import com.github.passerr.secretary.enums.EventType

/**
 * jira webhook 请求实体vo
 * @author xiehai
 * @date 2018/12/04 18:32
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class JiraVo {
    /**
     * 问题类型
     */
    String issueEventTypeName
    /**
     * 执行人 创建/更新
     */
    UserVo user
    /**
     * 问题明细
     */
    IssueVo issue

    String message() {
        return String.format(
            "【%s%s】已由【%s】【%s】，当前状态为：【%s】，%s!",
            this.issue.getFields().getIssueType().getName(),
            this.issue.getKey(),
            this.user.getDisplayName(),
            EventType.get(this.issueEventTypeName).getName(),
            this.issue.getFields().getStatus().getName(),
            this.issue.getUserMessage()
        )
    }

    UserVo user() {
        return this.issue.getAtUser()
    }
}
