package com.example.mobility.direction.controller

import com.example.mobility.client.KakaoNaviClient
import com.example.mobility.client.dto.req.ClientRequest
import com.example.mobility.client.dto.res.KakaoDirectionsResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/directions")
class DirectionsController(
    private val kakaoNaviClient: KakaoNaviClient
) {
    @PostMapping
    fun getDirections(@RequestBody req: ClientRequest): KakaoDirectionsResponse =
        kakaoNaviClient.oneToOneApi(req)
}