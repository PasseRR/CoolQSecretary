package com.github.passerr.secretary.api

import com.github.passerr.secretary.vo.cool.*
import retrofit2.Call
import retrofit2.http.Body
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
     * @param req 消息内容
     * @return 应答
     */
    @POST("/send_group_msg")
    Call<CoolResp<Void>> sendGroupMsg(@Body SendGroupMessageReq req)

    /**
     * 讨论组消息发送
     * @param req 消息内容
     * @return 应答
     */
    @POST("/send_discuss_msg")
    Call<CoolResp<Void>> sendDiscussMsg(@Body SendDiscussMessageReq req)

    /**
     * 私人消息发送
     * @param req 消息内容
     * @return 应答
     */
    @POST("/send_private_msg")
    Call<CoolResp> sendPrivateMsg(@Body SendPrivateMessageReq req)

    /**
     * 发送消息
     * @param req 消息内容
     * @return 应答
     */
    @POST("/send_msg")
    Call<CoolResp<CoolMessageResp>> sendMsg(@Body SendAllMessageReq req)

    /**
     * 异步发送消息
     * @param req 消息内容
     * @return 应答
     */
    @POST("/send_msg_async")
    Call<CoolResp<CoolMessageResp>> sendMsgAsync(@Body SendAllMessageReq req)
}