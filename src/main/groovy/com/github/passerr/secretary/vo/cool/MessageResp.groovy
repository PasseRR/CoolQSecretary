package com.github.passerr.secretary.vo.cool

/**
 * 应答消息
 * @author xiehai
 * @date 2018/12/04 17:53
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class MessageResp {
    String reply
    boolean autoEscape
    boolean atSender = true

    MessageResp(String reply) {
        this.reply = reply
    }

    MessageResp(String reply, boolean autoEscape) {
        this.reply = reply
        this.autoEscape = autoEscape
    }
}
