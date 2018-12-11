package com.github.passerr.secretary.vo.jira

import com.google.gson.annotations.SerializedName

/**
 * jira用户实体vo
 * @author xiehai
 * @date 2018/12/04 20:59
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class UserVo {
    String name
    String key
    @SerializedName("displayName")
    String displayName

    static main(agrs) {
        println "hello world"
    }
}
