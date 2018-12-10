package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.jira.SearchResultVo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
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
     * @param header token头
     * @param jql jql语句
     * @return {@link SearchResultVo}
     */
    @GET("/rest/api/2/search")
    Call<SearchResultVo> search(@Header("Authorization") String header, @Query("jql") String jql)
}