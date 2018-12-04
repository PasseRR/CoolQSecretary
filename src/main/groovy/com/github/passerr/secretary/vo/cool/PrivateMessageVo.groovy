package com.github.passerr.secretary.vo.cool

/**
 * 私聊消息
 * @author xiehai
 * @date 2018/12/04 16:07
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class PrivateMessageVo extends MessageVo {
    /**
     * 消息子类型
     */
    String subType
    @Override
    boolean needReply() {
        // 私人消息都要回复
        return true
    }
}
