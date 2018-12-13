package com.github.passerr.secretary.vo.jira

/**
 * jira附件
 * @author xiehai
 * @date 2018/12/13 17:33
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class AttachmentVo {
    String id
    String filename
    String content

    def getAttachmentUrl() {
        URLDecoder.decode(this.content, "UTF-8")
    }
}
