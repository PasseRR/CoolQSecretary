package com.github.passerr.secretary.vo.jira

import com.google.gson.annotations.SerializedName

/**
 * jsql查询结果
 * @author xiehai
 * @date 2018/12/10 15:07
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class SearchResultVo {
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
     * 问题明细
     */
    List<IssueVo> issues

    /**
     * 将问题明细转为消息发出
     * @param type 问题类型
     * @return 消息内容
     */
    String toQqMessage(String type){
        return Optional.ofNullable(this.total)
            .filter({n -> n > 0})
            .map({n ->
                StringBuilder sb = new StringBuilder("以下是你的${type}列表:\n")
                issues.eachWithIndex { item, index ->
                    sb.append(index + 1)
                        .append(". ")
                        .append(item.toQqMessage())
                        .append("\n")
                }
                return sb.substring(0, sb.length() - 1)
            })
            .orElse("你很棒棒哦, 所有${type}都已经处理完毕!")
    }
}
