package com.github.passerr.secretary.constants

/**
 * 酷Q常量
 * @author xiehai
 * @date 2018/12/05 11:39
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface CoolQConstants {
    /**
     * 上报类型 消息
     */
    String POST_TYPE_MESSAGE = "message"
    /**
     * 上报类型 群、讨论组变动通知事件
     */
    String POST_TYPE_NOTICE = "notice"
    /**
     * 上报类型 加好友、加群请求/邀请
     */
    String POST_TYPE_REQUEST = "request"

    /**
     * 消息类型 私人消息
     */
    String MESSAGE_TYPE_PRIVATE = "private"
    /**
     * 消息类型 群消息
     */
    String MESSAGE_TYPE_GROUP = "group"
    /**
     * 消息类型 讨论组消息
     */
    String MESSAGE_TYPE_DISCUSS = "discuss"
}