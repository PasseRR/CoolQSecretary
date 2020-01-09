package com.github.passerr.secretary.command.gitlab

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 部署命令
 * @date 2020/01/09 17:52
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class DeployCmd extends Command<String> {
    DeployCmd(Function<String, String> function) {
        super(function)
    }

    @Override
    String execute(MessageReq messageReq) {
        return super.executor.apply(super.parameter(messageReq.legalMessage))
    }

    @Override
    protected Matcher matchCn(String message) {
        return message =~ /部署 ([\s\S]+)/
    }

    @Override
    protected Matcher matchEn(String message) {
        return message =~ /deploy ([\s\S]+)/
    }
}
