package com.github.passerr.secretary.vo.jira

import groovy.transform.PackageScope

/**
 * jira任务实体
 * @author xiehai
 * @date 2018/12/04 21:00
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
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

    protected String getAtUser() {
        return this.fields.isDone() ? this.fields.getCreator() : this.fields.getAssignee()
    }

    /**
     * 转为qq查询消息
     * @return 问题概述
     */
    @PackageScope
    String toQqMessage() {
        return String.format(
            "%s %s %s %s",
            this.key,
            this.fields.getIssueType().getName(),
            this.fields.getStatus().getName(),
            this.fields.getSummary()
        )
    }
}
