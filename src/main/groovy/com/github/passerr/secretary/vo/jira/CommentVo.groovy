package com.github.passerr.secretary.vo.jira

import com.google.gson.annotations.SerializedName

import java.text.SimpleDateFormat

/**
 * jira备注实体
 * @author xiehai
 * @date 2018/12/11 09:25
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class CommentVo {
    /**
     * comment id
     */
    String id
    /**
     * comment url
     */
    String self
    /**
     * 作者
     */
    UserVo author
    /**
     * 备注内容
     */
    String body
    /**
     * 更新人
     */
    @SerializedName("updateAuthor")
    UserVo updateAuthor
    /**
     * 创建时间
     */
    String created
    /**
     * 更新时间
     */
    String updated

    /**
     * 时间字符串格式化
     * @return yyyy-MM-dd HH:mm:ss
     */
    private String getUpdated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"))

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(simpleDateFormat.parse(this.updated))
    }

    /**
     * 备注格式化为qq消息体
     * @return 消息内容
     */
    String toQqMessage() {
        return "${this.updateAuthor.getDisplayName()} ${this.body.replaceAll("\r\n", "\n")} ${this.getUpdated()}"
    }
}
