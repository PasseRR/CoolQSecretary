package com.github.passerr.secretary.command.jira

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 问题命令
 * @author xiehai
 * @date 2018/12/27 13:47
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class IssueCmd extends Command<Long> {
    IssueCmd(Function<Long, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /我的问题/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /issue/
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(messageReq.getUserId())
    }
}
