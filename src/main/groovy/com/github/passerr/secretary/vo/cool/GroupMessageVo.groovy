package com.github.passerr.secretary.vo.cool

/**
 * 群消息
 * @author xiehai
 * @date 2018/12/04 16:21
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class GroupMessageVo extends MessageVo {
    /**
     * 群id
     */
    Long groupId
    /**
     * 消息子类型
     */
    String subType

    @Override
    boolean needReply() {
        return super.includeAtMe()
    }
}
