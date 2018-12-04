package com.github.passerr.secretary.config

import com.github.passerr.secretary.api.CoolQApi
import com.github.passerr.secretary.api.ItpkApi
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * retrofit配置
 * @author xiehai
 * @date 2018/12/04 16:44
 * @Copyright ( c ) tellyes tech. inc. co.,ltd
 */
@Configuration
class RetrofitConfig {
    @Value("\${secretary.cool.url}")
    String coolQUrl
    @Value("\${secretary.itpk.url}")
    String itpkUrl
    @Autowired
    Gson gson

    @Bean
    Retrofit cool() {
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(this.gson))
                              .baseUrl(this.coolQUrl)
                              .build()
    }

    @Bean
    Retrofit itpk() {
        new Retrofit.Builder().baseUrl(this.itpkUrl).build()
    }

    @Bean
    CoolQApi coolQApi() {
        this.cool().create(CoolQApi.class)
    }

    @Bean
    ItpkApi itpkApi() {
        this.itpk().create(ItpkApi.class)
    }
}
