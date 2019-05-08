package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.gitlab.UserVo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * gitlab api
 * @Copyright (c)tellyes tech. inc. co.,ltd
 * @date 2019/05/08 10:14
 * @author xiehai
 */
interface GitlabApi {
    @GET("/api/v4/users")
    Call<List<UserVo>> findUsers(@Query("username") String username)
}