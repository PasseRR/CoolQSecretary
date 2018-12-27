package com.github.passerr.secretary.command.itpk

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 莱利机器人问答设置
 * @author xiehai
 * @date 2018/12/27 15:54
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class QuestionAnswerCmd extends Command<String> {
    QuestionAnswerCmd(Function<String, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /茉莉问答 ([\s\S]+)/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /itpkqa ([\s\S]+)/
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(super.parameter(messageReq.getLegalMessage()))
    }
}
