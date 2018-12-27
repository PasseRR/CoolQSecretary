package com.github.passerr.secretary.command.jira

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 问题详情命令
 * @author xiehai
 * @date 2018/12/27 15:27
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class DetailCmd extends Command<String> {
    DetailCmd(Function<String, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /详情 (\w+-\d+)/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /detail (\w+-\d+)/
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(super.parameter(messageReq.getLegalMessage()))
    }
}
