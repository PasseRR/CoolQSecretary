package com.github.passerr.secretary.command.jira

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * bug命令
 * @author xiehai
 * @date 2018/12/27 14:47
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class BugCmd extends Command<Long> {
    BugCmd(Function<Long, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /我的缺陷/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /bug/
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(messageReq.getUserId())
    }
}
