package com.github.passerr.secretary.vo.cool

import groovy.transform.Canonical

/**
 * 酷q发送消息类型及message类型
 * @author xiehai
 * @date 2018/12/04 15:45
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Canonical
class TypeReq {
    /**
     * 上报类型
     */
    String postType
    /**
     * 消息类型
     */
    String messageType
}
