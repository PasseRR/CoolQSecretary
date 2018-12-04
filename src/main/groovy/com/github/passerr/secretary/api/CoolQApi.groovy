package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.cool.CoolResp
import com.github.passerr.secretary.vo.cool.SendDiscussMessageReq
import com.github.passerr.secretary.vo.cool.SendGroupMessageReq
import com.github.passerr.secretary.vo.cool.SendPrivateMessageReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * 酷Q http消息发送接口
 * @author xiehai
 * @date 2018/12/04 17:07
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
interface CoolQApi {
    /**
     * 群消息发送
     * @param req 请求体
     * @return 消息编号
     */
    @POST("/send_group_msg")
    @Headers("Content-Type:application/json")
    Call<CoolResp> sendGroupMsg(@Header("Authorization") String header, @Body SendGroupMessageReq req)

    /**
     * 讨论组消息发送
     * @param req 请求体
     * @return 消息编号
     */
    @POST("/send_discuss_msg")
    @Headers("Content-Type:application/json")
    Call<CoolResp> sendDiscussMsg(@Header("Authorization") String header, @Body SendDiscussMessageReq req)

    /**
     * 私人消息发送
     * @param req 请求体
     * @return 消息编号
     */
    @POST("/send_private_msg")
    @Headers("Content-Type:application/json")
    Call<CoolResp> sendPrivateMsg(@Header("Authorization") String header, @Body SendPrivateMessageReq req)
}