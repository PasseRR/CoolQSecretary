package com.github.passerr.secretary.vo.jira

import groovy.transform.PackageScope

/**
 * jira任务实体
 * @author xiehai* @date 2018/12/04 21:00
 * @Copyright (c) tellyes tech. inc. co.,ltd
 */
class IssueVo {
    String id
    String self
    /**
     * 任务编号
     */
    String key
    /**
     * 任务字段信息
     */
    FieldsVo fields

    private String getIssueUrl() {
        return this.self.replace("/rest/api/2/issue/" + this.id, "/browse/" + this.key)
    }

    protected String getUserMessage() {
        return String.format("请前往【%s】查看", this.getIssueUrl())
    }

    protected UserVo getAtUser() {
        return (this.fields.isDone() || !this.fields.assignee) ? this.fields.creator : this.fields.assignee
    }

    /**
     * 转为qq查询消息
     * @return 问题概述
     */
    @PackageScope
    String toQqMessage() {
        return String.format(
            "%s【%s】 %s %s",
            this.fields.getIssueType().getName(),
            this.key,
            this.fields.getStatus().getName(),
            this.fields.getSummary()
        )
    }

    String toDetailQqMessage() {
        String.format(
            "%s %s\n%s\n%s",
            this.toQqMessage(),
            this.fields.getAssignee() ? this.fields.getAssignee().getDisplayName() : "暂无经办人",
            (this.fields.getDescription() ?: "暂无描述").replaceAll("\r\n", "\n"),
            this.fields.toAttachmentQqMessage()
        )
    }
}
