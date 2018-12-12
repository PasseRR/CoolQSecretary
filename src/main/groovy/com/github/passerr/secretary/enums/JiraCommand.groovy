package com.github.passerr.secretary.enums

/**
 * jira命令枚举
 * @author xiehai
 * @date 2018/12/10 17:26
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
enum JiraCommand {
    DONE_CN("完成"),
    DONE_EN("done"),
    REMARK_CN("备注"),
    REMARK_EN("remark")

    String command

    JiraCommand(String command) {
        this.command = command
    }

    /**
     * 解析消息中的问题编号
     * @param message 消息内容
     * @return 问题编号
     */
    String getIssueKey(String message) {
        return message?.replace(this.command + " ", "")
    }
}