package com.github.passerr.secretary.vo.message

/**
 * 会议室消息提醒
 * @date 2020/04/16 10:52
 * @Copyright (c) wisewe co.,ltd
 * @author xiehai
 */
class MessageVo {
    /**
     * qq号码
     */
    Long id
    /**
     * 消息类型 private/discuss/group
     */
    String type
    /**
     * 消息内容
     */
    String message
}
