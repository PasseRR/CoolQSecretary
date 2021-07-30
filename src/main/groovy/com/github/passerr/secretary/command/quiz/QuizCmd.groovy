package com.github.passerr.secretary.command.quiz

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.GroupMessageReq
import com.github.passerr.secretary.vo.cool.MessageReq
import com.github.passerr.secretary.vo.quiz.MatchOrderVo

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 答题命令
 * @date 2021/07/29 18:28
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class QuizCmd extends Command<MatchOrderVo> {
    QuizCmd(Function<MatchOrderVo, String> function) {
        super(function)
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(
            new MatchOrderVo(
                groupId: (messageReq as GroupMessageReq).groupId
            )
        )
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /答题/
    }

    @Override
    protected Matcher matchEn(String message) {
        message =~ /quiz/
    }
}
