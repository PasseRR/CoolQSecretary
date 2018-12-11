package com.github.passerr.secretary.vo.jira

import com.google.gson.annotations.SerializedName

/**
 * 备注列表
 * @author xiehai
 * @date 2018/12/11 09:24
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class SearchCommentVo {
    /**
     * 开始索引
     */
    @SerializedName("startAt")
    Integer startAt
    /**
     * 结果数量
     */
    @SerializedName("maxResults")
    Integer maxResults
    /**
     * 总数量
     */
    Integer total
    /**
     * 备注列表
     */
    List<CommentVo> comments

    String toQqMessage(String key) {
        return Optional.ofNullable(this.total)
                       .filter({ n -> n > 0 })
                        .map({n ->
                            StringBuilder sb = new StringBuilder("以下是${key}的备注列表:\n")
                            comments.eachWithIndex { CommentVo entry, int i ->
                                sb.append("${i+1}. ${entry.toQqMessage()}\n")
                            }
                            return sb.substring(0, sb.length()-1)
                        })
                        .orElse("${key}暂无备注")
    }
}
