package com.example.mobility.kakao.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${kakao.navi.base-url}") private val baseUrl: String,
    @Value("\${kakao.navi.rest-api-key}") private val apiKey: String
) {

    @Bean
    fun kakaoNaviWebClient(): WebClient =
        WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader("Authorization", "KakaoAK $apiKey")
            .build()

    //todo 카카오맵 클라이언트 연결
}