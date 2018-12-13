package com.github.passerr.secretary.vo.jira

import com.github.passerr.secretary.component.JiraConfigUtil
import com.google.gson.annotations.SerializedName

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
    /**
     * 概述
     */
    String summary
    /**
     * 描述
     */
    String description
    /**
     * 附件列表
     */
    List<AttachmentVo> attachment

    boolean isDone() {
        return JiraConfigUtil.isDone(this.status.getStatusCategory().getId())
    }

    String getCreatorKey() {
        return this.creator.getKey()
    }

    String getAssigneeKey() {
        return this.assignee.getKey()
    }

    String toAttachmentQqMessage() {
        StringBuilder sb = new StringBuilder()
        attachment?.eachWithIndex { AttachmentVo entry, int i ->
            sb.append("附件${i + 1}. ")
              .append(entry.getAttachmentUrl())
              .append("\n")
        }

        sb.length() > 1 ? sb.substring(0, sb.length() - 1) : ""
    }
}
