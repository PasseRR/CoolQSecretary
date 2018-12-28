package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.jira.IssueVo
import com.github.passerr.secretary.vo.jira.SearchCommentVo
import com.github.passerr.secretary.vo.jira.SearchResultVo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * jira rest api
 * @author xiehai
 * @date 2018/12/10 10:14
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface JiraApi {
    /**
     * jira问题查询
     * @param jql jql语句
     * @return {@link SearchResultVo}
     */
    @GET("/rest/api/2/search")
    Call<SearchResultVo> search(@Query("jql") String jql)

    /**
     * 根据问题key或id查询问题备注
     * @param key issue key
     * @return {@link SearchCommentVo}
     */
    @GET("/rest/api/2/issue/{issueIdOrKey}/comment")
    Call<SearchCommentVo> searchComments(@Path("issueIdOrKey") String key)

    /**
     * 获取问题详情
     * @param key issue key
     * @return {@link IssueVo}
     */
    @GET("/rest/api/2/issue/{issueIdOrKey}")
    Call<IssueVo> getIssue(@Path("issueIdOrKey") String key)
}