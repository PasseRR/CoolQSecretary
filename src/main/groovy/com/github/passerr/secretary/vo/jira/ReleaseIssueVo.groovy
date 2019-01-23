package com.github.passerr.secretary.vo.jira

/**
 * jira问题发布vo
 * @author xiehai
 * @date 2019/01/23 17:00
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
class ReleaseIssueVo {
    /**
     * 问题处理开始时间
     */
    String start
    /**
     * 问题处理结束时间
     */
    String end
    /**
     * 问题环境前缀
     */
    String env
    /**
     * 发布时间
     */
    String releaseTime
}
