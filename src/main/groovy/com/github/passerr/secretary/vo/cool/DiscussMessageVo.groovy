package com.github.passerr.secretary.vo.cool

/**
 * 讨论组消息
 * @author xiehai
 * @date 2018/12/04 16:21
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class DiscussMessageVo extends MessageVo {
    /**
     * 讨论组id
     */
    Long discussId

    @Override
    boolean needReply() {
        return super.includeAtMe()
    }
}
