package com.github.passerr.secretary.vo.jira

import com.google.gson.annotations.SerializedName

import java.util.stream.Stream

/**
 * jira任务字段实体
 * @author xiehai
 * @date 2018/12/04 21:01
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class FieldsVo {
    /**
     * 问题类型
     */
    @SerializedName("issuetype")
    IssueTypeVo issueType
    /**
     * 经办人
     */
    UserVo assignee
    /**
     * 报告人
     */
    UserVo creator
    /**
     * 任务状态
     */
    StatusVo status

    protected boolean isDone() {
        return Stream.of("3", "10002")
                     .anyMatch(this.status.getId().&equals)
    }

    protected String getCreator() {
        return this.creator.getKey()
    }

    protected String getAssignee() {
        return this.assignee.getKey()
    }
}
