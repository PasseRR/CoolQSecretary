package com.github.passerr.secretary.constants

/**
 * 帮助命令文档
 * @author xiehai
 * @date 2018/12/13 14:12
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface HelpDoc {
    String DOC = """命令分为中文和英文命令，同样的功能中文和英文命令结果是一致的，所有示例均为私聊时(没有加@话痨)英文命令。
所有命令在私聊时直接以命令开始，在聊天群、讨论组需要@话痨命令才会生效哟!
系统命令
    帮助，help\t\t\t\t查看帮助文档 e.g. help
    茉莉密码，itpkpwd\t\t\t机器人后台密码设置 e.g. itpkpwd 123456
    茉莉问答，itpkqa\t\t\t机器人对话学习，问题和回答用'|'分隔 e.g. itpkqa 1+1=?|2
    茉莉随机回复，itpkrr\t\t机器人随机回复，当机器人不知道怎么回答时，可能会这样回复 e.g. itpkrr 我也不知道怎么说
JIRA命令
    我的问题，issue\t\t\t查询jira所有未完成问题包括任务及bug e.g. issue
    我的任务，task\t\t\t查询jira所有未完成任务 e.g. task
    我的缺陷，bug\t\t\t查询jira所有未完成bug e.g. bug
    备注，remark\t\t\t查询问题备注 e.g. remark EDUADMIN-973
"""
}