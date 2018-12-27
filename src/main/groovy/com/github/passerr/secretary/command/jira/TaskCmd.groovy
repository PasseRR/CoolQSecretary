package com.github.passerr.secretary.command.jira

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 我的任务
 * @author xiehai
 * @date 2018/12/27 13:48
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class TaskCmd extends Command<Long> {
    TaskCmd(Function<Long, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /我的任务/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /task/
    }

    @Override
    String execute(MessageReq messageReq) {
        return super.executor.apply(messageReq.getUserId())
    }
}
