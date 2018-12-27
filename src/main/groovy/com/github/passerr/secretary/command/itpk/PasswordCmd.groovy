package com.github.passerr.secretary.command.itpk

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * itpk密码设置命令
 * @author xiehai
 * @date 2018/12/27 15:48
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class PasswordCmd extends Command<String> {
    PasswordCmd(Function<String, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /茉莉密码 ([\s\S]+)/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /itpkpwd ([\s\S]+)/
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(super.parameter(messageReq.getLegalMessage()))
    }
}
