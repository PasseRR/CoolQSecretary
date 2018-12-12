package com.github.passerr.secretary.vo.cool

import groovy.transform.Canonical

/**
 * 通用消息vo
 * @author xiehai
 * @date 2018/12/04 16:12
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Canonical
abstract class MessageReq {
    /**
     * 消息id
     */
    Integer messageId
    /**
     * 机器人qq
     */
    Long selfId
    /**
     * 发送人qq
     */
    Long userId
    /**
     * 消息内容
     */
    String message
    /**
     * 原始消息内容
     */
    String rawMessage
    /**
     * 发送时间戳
     */
    Long time

    /**
     * 是否需要回复消息
     * @return true/false
     */
    abstract boolean needReply()

    /**
     * 获得消息
     * @return
     */
    String getLegalMessage() {
        // 如果包含@我 自动替换掉@我为空
        return this.includeAtMe() ?
            this.rawMessage.replaceAll(String.format("\\[CQ:at,qq=%d\\] ", this.selfId), "")?.trim() :
            this.message?.trim()
    }

    /**
     * 消息是否在@我
     * @return true/false
     */
    protected boolean includeAtMe() {
        return this.rawMessage.contains(String.format("[CQ:at,qq=%d]", this.selfId))
    }
}
