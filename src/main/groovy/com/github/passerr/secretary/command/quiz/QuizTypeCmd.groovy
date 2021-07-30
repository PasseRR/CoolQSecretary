package com.github.passerr.secretary.command.quiz

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 答题分类命令
 * @date 2021/07/29 18:28
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class QuizTypeCmd extends Command<Void> {
    QuizTypeCmd(Function<Void, String> function) {
        super(function)
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(null)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /答题分类/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /quiztype/
    }
}
