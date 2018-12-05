package com.github.passerr.secretary.vo.cool

import static com.github.passerr.secretary.constants.CoolQConstants.MESSAGE_TYPE_DISCUSS
import static com.github.passerr.secretary.constants.CoolQConstants.MESSAGE_TYPE_GROUP

/**
 * 发送所有消息
 * @author xiehai
 * @date 2018/12/05 11:45
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class SendAllMessageReq extends SendMessageReq {
    Long userId
    Long groupId
    Long discussId

    /**
     * 设置id
     * @param type 根据不同类型发送不同消息
     * @param id 用户qq/群qq/讨论组qq
     */
    void setId(String type, Long id) {
        switch (type) {
            case MESSAGE_TYPE_DISCUSS:
                this.setDiscussId(id)
                break
            case MESSAGE_TYPE_GROUP:
                this.setGroupId(id)
                break
            default:
                this.setUserId(id)
                break
        }
    }
}
