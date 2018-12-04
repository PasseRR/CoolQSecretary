package com.github.passerr.secretary.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 茉莉机器人http api
 * @author xiehai
 * @date 2018/12/04 17:08
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface ItpkApi {
    /**
     * 茉莉机器人提问
     * @param apiKey apiKey
     * @param apiSecret apiSecret
     * @param question 问题内容
     * @return 回答
     */
    @GET("api.php")
    Call<ResponseBody> question(
        @Query("api_key") String apiKey,
        @Query("api_secret") String apiSecret,
        @Query(value = "question", encoded = true) String question)
}