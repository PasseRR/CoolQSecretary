package com.github.passerr.secretary.vo.jira

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
}
