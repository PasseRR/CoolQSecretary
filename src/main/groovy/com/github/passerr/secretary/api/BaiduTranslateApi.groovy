package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.baidu.TranslateResp
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 百度翻译api
 * @author xiehai
 * @date 2018/12/27 16:24
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface BaiduTranslateApi {
    /**
     * 百度翻译api
     * @param body 请求报文
     * @return {@link TranslateResp}
     */
    @POST("/api/trans/vip/translate")
    @FormUrlEncoded
    Call<TranslateResp> translate(@FieldMap Map<String, String> body)
}
