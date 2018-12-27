package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.BaiduTranslateApi
import com.github.passerr.secretary.vo.baidu.TranslateReq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * 百度翻译等组件
 * @author xiehai
 * @date 2018/12/27 16:46
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
class BaiduComponent {
    @Autowired
    BaiduTranslateApi baiduTranslateApi
    @Value("\${secretary.baidu.translate.appId}")
    Long appId
    @Value("\${secretary.baidu.translate.appSecret}")
    String appSecret

    /**
     * 百度翻译
     * @param to 目标语言
     * @param source 源语言文本
     * @return 目标语言文本
     */
    String translate(TranslateReq req) {
        Map<String, String> body = new HashMap<>(8)
        body.put("from", "auto")
        body.put("to", req.getTo())
        body.put("q", req.getSource())
        body.put("appid", "${this.appId}")
        // 随机盐
        long salt = System.currentTimeMillis()
        body.put("salt", "${salt}")
        // 签名
        body.put("sign", (this.appId + req.getSource() + salt + this.appSecret).md5())

        this.baiduTranslateApi.translate(body).execute().body().toMessage()
    }
}
