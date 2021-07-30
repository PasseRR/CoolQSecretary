package com.github.passerr.secretary.command.robot

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.regex.Matcher

/**
 * 帮助命令
 * @author xiehai* @date 2018/12/27 13:43
 * @Copyright (c) tellyes tech. inc. co.,ltd
 */
class HelpCmd extends Command {
    @Override
    protected Matcher matchCn(String message) {
        message =~ /帮助/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /help/
    }

    @Override
    String execute(MessageReq messageReq) {
        """命令分为中文和英文命令，同样的功能中文和英文命令结果是一致的，所有示例均为私聊时(没有加@话痨)英文命令。
所有命令在私聊时直接以命令开始，在聊天群、讨论组需要@话痨命令才会生效哟!
系统命令
    帮助，help            查看帮助文档 e.g. 【help】
    茉莉问答，itpkqa       机器人对话学习，问题和回答用'|'分隔 e.g. 【itpkqa 1+1=?|2】
    茉莉随机回复，itpkrr   机器人随机回复，当机器人不知道怎么回答时，可能会这样回复 e.g. 【itpkrr 你说啥?】
    翻译帮助，thelp        查看翻译支持语言
    中文 hello，zh hello   翻译命令 e.g. 【中文 hello】【en 你是谁】
JIRA命令
    我的问题，issue        查询jira所有未完成问题包括任务及bug e.g. 【issue】
    我的任务，task         查询jira所有未完成任务 e.g. 【task】
    我的缺陷，bug          查询jira所有未完成bug e.g. 【bug】
    详情，detail          查询问题详情 e.g. 【detail EDUADMIN-973】
    备注，remark          查询问题备注 e.g. 【remark EDUADMIN-973】
答题命令
    答题分类，quiztype       答题分类    e.g. 【答题分类/quiztype】
    答题，quiz             随机答题    e.g. 【答题/quiz】
    分类答题，typequiz       按照分类答题 e.g.【分类答题/typequiz 文学】"""
    }
}
