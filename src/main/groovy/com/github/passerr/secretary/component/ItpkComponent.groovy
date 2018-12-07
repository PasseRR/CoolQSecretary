package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.ItpkApi
import com.github.passerr.secretary.vo.itpk.JokeVo
import com.google.gson.Gson
import groovy.transform.PackageScope
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * 茉莉机器人组件
 * @author xiehai
 * @date 2018/12/04 17:17
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
@Slf4j
@PackageScope
class ItpkComponent {
    @Value("\${secretary.itpk.apiKey}")
    String apiKey
    @Value("\${secretary.itpk.apiSecret}")
    String apiSecret
    @Autowired
    ItpkApi itpkApi
    @Autowired
    Gson gson

    /**
     * 茉莉机器人对话应答
     * @param question 问题
     * @return 应答
     */
    String message(String question) {
        try {
            def response = this.itpkApi.question(this.apiKey, this.apiSecret, question).execute()
            def message = response.body().string()
            if (message.startsWith("{") && message.endsWith("}")) {
                def jokeVo = this.gson.fromJson(message, JokeVo.class)
                return jokeVo.getContent()
            }

            return message
        } catch (Exception ignore) {
            return "网络出问题了!"
        }
    }

    /**
     * 成语接龙
     * @param question 成语
     * @return 应答成语
     */
    String phrase(String question) {
        def resp = this.message("@cy" + question)
        return resp.length() == 4 ? resp : this.message(question)
    }
}
