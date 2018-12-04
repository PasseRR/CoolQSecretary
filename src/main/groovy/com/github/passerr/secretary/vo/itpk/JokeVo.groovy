package com.github.passerr.secretary.vo.itpk

import groovy.transform.Canonical

/**
 * 茉莉机器人笑话应答
 * @author xiehai
 * @date 2018/12/04 17:44
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Canonical
class JokeVo {
    /**
     * 笑话标题
     */
    String title
    /**
     * 笑话内容
     */
    String content
}
