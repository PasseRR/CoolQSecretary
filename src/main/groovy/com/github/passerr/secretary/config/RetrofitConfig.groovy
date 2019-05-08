package com.github.passerr.secretary.config


import com.github.passerr.secretary.api.BaiduTranslateApi
import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.api.GitlabApi
import com.github.passerr.secretary.api.ItpkApi
import com.github.passerr.secretary.api.ItpkSettingApi
import com.github.passerr.secretary.api.JiraApi
import com.google.gson.Gson
import groovy.util.logging.Slf4j
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.nio.charset.Charset

/**
 * retrofit配置
 * @author xiehai* @date 2018/12/04 16:44
 * @Copyright (c) tellyes tech. inc. co.,ltd
 */
@Configuration
@Slf4j
class RetrofitConfig {
    @Value("\${secretary.cool.url}")
    String coolQUrl
    @Value("\${secretary.cool.token}")
    String coolQToken
    @Value("\${secretary.itpk.url}")
    String itpkUrl
    @Value("\${secretary.itpk.robot_url}")
    String itpkRobotUrl
    @Value("\${secretary.jira.url}")
    String jiraUrl
    @Value("\${secretary.jira.token}")
    String jiraToken
    @Value("\${secretary.baidu.translate.url}")
    String translateUrl
    @Value("\${secretary.gitlab.url}")
    String gitlabUrl
    @Value("\${secretary.gitlab.token}")
    String gitlabToken
    @Autowired
    Gson gson

    @Bean
    OkHttpClient coolHttpClient() {
        new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            Response intercept(Interceptor.Chain chain) throws IOException {
                // 添加授权token
                Request request = chain.request()
                                       .newBuilder()
                                       .addHeader("Content-Type", "application/json")
                                       .addHeader("Authorization", "Token ${RetrofitConfig.this.coolQToken}")
                                       .build()

                log.debug("${request.headers()}")
                // post日志记录
                if (request.body()) {
                    Buffer buffer = new Buffer()
                    request.body().writeTo(buffer)
                    log.debug(buffer.readString(Charset.forName("UTF-8")))
                }

                chain.proceed(request)
            }
        }).build()
    }

    @Bean
    Retrofit cool() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(this.gson))
                              .client(this.coolHttpClient())
                              .baseUrl(this.coolQUrl)
                              .build()
    }

    @Bean
    OkHttpClient jiraHttpClient() {
        new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            Response intercept(Interceptor.Chain chain) throws IOException {
                // 添加授权token
                Request request = chain.request()
                                       .newBuilder()
                                       .addHeader("Authorization", "Basic ${RetrofitConfig.this.jiraToken}")
                                       .build()

                log.debug("${request.headers()}")
                // post日志记录
                if (request.body()) {
                    Buffer buffer = new Buffer()
                    request.body().writeTo(buffer)
                    log.debug(buffer.readString(Charset.forName("UTF-8")))
                }

                chain.proceed(request)
            }
        }).build()
    }

    @Bean
    Retrofit jira() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(this.gson))
                              .client(this.jiraHttpClient())
                              .baseUrl(this.jiraUrl)
                              .build()
    }

    @Bean
    Retrofit itpk() {
        new Retrofit.Builder().baseUrl(this.itpkUrl).build()
    }

    @Bean
    Retrofit itpkRobot() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(this.gson))
                              .baseUrl(this.itpkRobotUrl)
                              .build()
    }

    @Bean
    Retrofit translate() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(this.gson))
                              .baseUrl(this.translateUrl)
                              .build()
    }

    @Bean
    OkHttpClient gitlabHttpClient() {
        new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            Response intercept(Interceptor.Chain chain) throws IOException {
                // 添加授权token
                Request request = chain.request()
                                       .newBuilder()
                                       .addHeader("Content-Type", "application/json")
                                       .addHeader("Private-Token", RetrofitConfig.this.gitlabToken)
                                       .build()

                log.debug("${request.headers()}")
                // post日志记录
                if (request.body()) {
                    Buffer buffer = new Buffer()
                    request.body().writeTo(buffer)
                    log.debug(buffer.readString(Charset.forName("UTF-8")))
                }

                chain.proceed(request)
            }
        }).build()
    }

    @Bean
    Retrofit gitlab() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(this.gson))
                              .client(this.gitlabHttpClient())
                              .baseUrl(this.gitlabUrl)
                              .build()
    }


    @Bean
    CoolQApi coolQApi() {
        this.cool().create(CoolQApi)
    }

    @Bean
    ItpkApi itpkApi() {
        this.itpk().create(ItpkApi)
    }

    @Bean
    JiraApi jiraApi() {
        this.jira().create(JiraApi)
    }

    @Bean
    ItpkSettingApi itpkSettingApi() {
        this.itpkRobot().create(ItpkSettingApi)
    }

    @Bean
    BaiduTranslateApi baiduTranslateApi() {
        this.translate().create(BaiduTranslateApi)
    }

    @Bean
    GitlabApi gitlabApi() {
        this.gitlab().create(GitlabApi)
    }
}
