package com.github.passerr.secretary.vo.baidu

import com.google.gson.annotations.SerializedName
import groovy.transform.builder.Builder

/**
 * 百度翻译请求实体
 * @author xiehai
 * @date 2018/12/27 16:33
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Builder(excludes = ["salt", "sign"])
class TranslateReq {
    /**
     * 翻译目标文本
     */
    @SerializedName("q")
    String source
    /**
     * 翻译源语言
     */
    String from
    /**
     * 翻译目标语言
     */
    String to
    /**
     * api appKey
     */
    @SerializedName("appid")
    Long appId
    /**
     * 随机数
     */
    Long salt
    /**
     * 签名 MD5(appId + source + salt + appSecret)小写
     */
    String sign

    /**
     * 签名及盐生成
     * @param appSecret app密钥
     */
    void sign(String appSecret) {
        this.salt = new Random().nextInt()
        this.sign = (this.appId + this.source + this.salt + appSecret).md5()
    }
}
