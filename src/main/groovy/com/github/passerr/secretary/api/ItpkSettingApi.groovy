package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.itpk.ItpkSettingRespVo
import retrofit2.Call
import retrofit2.http.*
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
    @Headers("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) \
Chrome/70.0.3538.110 Safari/537.36")
    @FormUrlEncoded
    Call<ItpkSettingRespVo> login(@Field("login_name") String name, @Field("login_pass") String password)

    /**
     * 词组学习
     * @param ckq 问题
     * @param ckr 回答
     * @return {@link Call}
     */
    @POST("ajax-ck-save.php")
    @Headers("User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) \
Chrome/70.0.3538.110 Safari/537.36")
    @FormUrlEncoded
    Call<ItpkSettingRespVo> study(@Header("Cookie") String cookie, @Field("ckq") String ckq, @Field("ckr") String ckr)
}
