package com.github.passerr.secretary.command.robot

import com.github.passerr.secretary.command.Command
import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 成语命令
 * @author xiehai
 * @date 2018/12/27 15:43
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class PhraseCmd extends Command<String> {
    PhraseCmd(Function<String, String> function) {
        super(function)
    }

    @Override
    protected Matcher matchCn(String message) {
        message =~ /[\u4E00-\u9FA5]{4}/
    }

    @Override
    protected Matcher matchEn(String message) {
        // 只支持中文四字成语
        this.matchCn(message)
    }

    @Override
    String execute(MessageReq messageReq) {
        super.executor.apply(messageReq.getLegalMessage())
    }
}
