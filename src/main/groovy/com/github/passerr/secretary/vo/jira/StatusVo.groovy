package com.github.passerr.secretary.vo.jira

import com.google.gson.annotations.SerializedName

/**
 * 任务状态vo
 * @author xiehai
 * @date 2018/12/04 21:02
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class StatusVo {
    String id
    String name
    @SerializedName("iconUrl")
    String iconUrl
    @SerializedName("statusCategory")
    StatusCategoryVo statusCategory
}
