package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.itpk.ItpkSettingRespVo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * 茉莉机器人设置api
 * @author xiehai
 * @date 2018/12/13 09:16
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface ItpkSettingApi {
    /**
     * 用户登录
     * @param name 用户名
     * @param password 密码
     * @return {@link Call}
     */
    @POST("login.php")
    @Headers([
        "Content-Type: application/x-www-form-urlencoded",
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) \
Chrome/70.0.3538.110 Safari/537.36"
    ])
    @FormUrlEncoded
    Call<ItpkSettingRespVo> login(
        @Field(value = "login_name", encoded = true) String name,
        @Field(value = "login_pass", encoded = true) String password)

    /**
     * 词组学习
     * @param question 问题
     * @param answer 回答
     * @return {@link Call}
     */
    @POST("ajax-ck-save.php")
    @Headers(["Content-Type: application/x-www-form-urlencoded"])
    @FormUrlEncoded
    Call<ResponseBody> study(@Field("ckq") String question, @Field("ckr") String answer)
}
