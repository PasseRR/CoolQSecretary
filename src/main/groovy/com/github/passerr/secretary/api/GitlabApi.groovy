package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.gitlab.GitlabVo
import com.github.passerr.secretary.vo.gitlab.UserVo
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * gitlab api
 * @Copyright (c)tellyes tech. inc. co.,ltd
 * @date 2019/05/08 10:14
 * @author xiehai
 */
interface GitlabApi {
    /**
     * 获取用户
     * @param username 用户帐号
     * @return {@link List}
     */
    @GET("/api/v4/users")
    Call<List<UserVo>> findUsers(@Query("username") String username)

    /**
     * 触发pipeline
     * @param id 项目id
     * @param params 参数
     * @return {@link Call}
     */
    @POST("/api/v4/projects/{projectId}/trigger/pipeline")
    @FormUrlEncoded
    Call<GitlabVo> deploy(@Path("projectId") Long id, @FieldMap Map<String, String> params)

    /**
     * 获得项目成员
     * @param id 项目id
     * @return {@link List}
     */
    @GET("/api/v4/projects/{projectId}/users?per_page=1024")
    Call<List<UserVo>> members(@Path("projectId") Long id)
}