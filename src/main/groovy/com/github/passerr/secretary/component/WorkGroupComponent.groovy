package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.JiraApi
import com.github.passerr.secretary.vo.jira.ReleaseIssueVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 工作组功能
 * @author xiehai
 * @date 2019/01/23 16:15
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
class WorkGroupComponent {
    @Autowired
    JiraApi jiraApi
    @Autowired
    SendMessageComponent sendMessageComponent
    /**
     * release缺陷消息
     * @param releaseIssueVo 发布信息
     * @return 应答消息
     */
    String release(ReleaseIssueVo releaseIssueVo) {
        String jql = """type = 故障 and updatedDate >= "${releaseIssueVo.start}"\
and updatedDate <= "${releaseIssueVo.end}" and assignee in membersOf("DT3") \
and summary ~ "${releaseIssueVo.env}" and status = 完成"""
        println jql
        def body = this.jiraApi.search(jql).execute().body()
        if (body.issues) {
            // 发送群消息
            this.sendMessageComponent.sendJiraReleaseMsg(
                body.issues,
                releaseIssueVo.releaseTime,
                releaseIssueVo.env
            )
            return "收到"
        } else {
            return "该时间范围内没有bug处理完成"
        }
    }
}
