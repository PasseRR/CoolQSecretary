package com.github.passerr.secretary.enums
/**
 * 消息类型
 * @author xiehai
 * @date 2018/12/04 15:40
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
enum PostType {
    /**
     * 文本消息
     */
    MESSAGE("message"),
    /**
     * 群、讨论组变动通知事件
     */
    NOTICE("notice"),
    /**
     * 加好友、群消息
     */
    REQUEST("request")

    String type

    PostType(String type) {
        this.type = type
    }
}