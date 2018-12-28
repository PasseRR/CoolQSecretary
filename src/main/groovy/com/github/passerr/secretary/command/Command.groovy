package com.github.passerr.secretary.command

import com.github.passerr.secretary.vo.cool.MessageReq

import java.util.function.Function
import java.util.regex.Matcher

/**
 * 抽象命令
 * @author xiehai
 * @date 2018/12/27 13:37
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
abstract class Command<T> {
    /**
     * 命令执行器
     */
    protected Function<T, String> executor

    protected Command() {

    }

    protected Command(Function<T, String> function) {
        this.executor = function
    }

    /**
     * 是否满足命令
     * @param message 消息内容
     * @return true/false
     */
    boolean match(String message) {
        return this.matchCn(message).matches() || this.matchEn(message).matches()
    }

    /**
     * 命令应答
     * @param messageReq 消息体
     * @return String
     */
    abstract String execute(MessageReq messageReq)

    /**
     * 获得命令参数
     * @param message 消息内容
     * @return 处理后的消息 默认不处理消息
     */
    protected String parameter(String message) {
        def en = this.matchEn(message)
        en.matches() ? en.group(1) : this.matchCn(message).group(1)
    }

    /**
     * 是否满足中文命令
     * @param message 消息
     * @return true/false
     */
    protected abstract Matcher matchCn(String message)

    /**
     * 是否满足英文命令
     * @param message message
     * @return true/false
     */
    protected abstract Matcher matchEn(String message)
}
