package com.github.passerr.secretary.component

import com.github.passerr.secretary.api.ItpkSettingApi
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * 机器人设置组件
 * @author xiehai
 * @date 2018/12/13 10:14
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Component
@Slf4j
class ItpkSettingComponent {
    @Autowired
    ItpkSettingApi itpkSettingApi
    @Autowired
    Gson gson
    @Value("\${secretary.itpk.email}")
    String username
    /**
     * 机器人密码
     */
    String password
    /**
     * 登录成功后的cookie
     */
    String cookie

    /**
     * 登录机器人设置后台
     * @return 是否登录成功提示
     */
    String login(String password) {
        def execute = this.itpkSettingApi.login(this.username, password).execute()
        def body = execute.body()

        switch (body.getResult()) {
            case 1:
                def cookie = URLDecoder.decode(execute.headers()["Set-Cookie"], "UTF-8")
                log.debug("fetch cookie:${cookie}")
                this.cookie = cookie
                this.password = password
                return "密码设置成功"
            case 9:
                return "密码错误"
            default:
                log.warn(this.gson.toJson(body))
                return "未知错误"
        }
    }
}