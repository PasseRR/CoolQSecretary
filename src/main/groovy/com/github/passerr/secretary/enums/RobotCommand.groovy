package com.github.passerr.secretary.enums

/**
 * 机器人命令枚举
 * @author xiehai
 * @date 2018/12/10 17:26
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
enum RobotCommand {
    ITPK_PWD_CN("茉莉密码"),
    ITPK_PWD_EN("itpkpwd"),
    ITPK_QA_CN("茉莉问答"),
    ITPK_QA_EN("itpkqa"),
    ITPK_RANDOM_REPLY_CN("茉莉随机回复"),
    ITPK_RANDOM_REPLY_EN("itpkrr"),
    JIRA_DONE_CN("完成"),
    JIRA_DONE_EN("done"),
    JIRA_REMARK_CN("备注"),
    JIRA_REMARK_EN("remark")

    String command

    RobotCommand(String command) {
        this.command = command
    }

    /**
     * 解析命令中的option参数
     * @param message 消息内容
     * @return 问题编号
     */
    String getOption(String message) {
        return message?.replace(this.command, "")?.trim()
    }
}