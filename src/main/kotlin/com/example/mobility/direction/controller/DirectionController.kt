package com.example.mobility.direction.controller

import com.example.mobility.client.KakaoNaviClient
import com.example.mobility.client.dto.req.ClientRequest
import com.example.mobility.client.dto.res.CreateDirectionResponse
import com.example.mobility.client.dto.res.KakaoDirectionsResponse
import com.example.mobility.direction.dto.DirectionRequest
import com.example.mobility.direction.service.DirectionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/directions")
class DirectionsController(
    private val kakaoNaviClient: KakaoNaviClient,
    private val directionService: DirectionService
) {
    @PostMapping("/test")
    fun testDirections(@RequestBody req: ClientRequest) : KakaoDirectionsResponse{
        val response = getKakaoResponse(kakaoNaviClient, req)
        return response
    }


    @PostMapping
    fun createDirections(@RequestBody req: DirectionRequest) : ResponseEntity<CreateDirectionResponse?> {
        val kakaoRes = getKakaoResponse(kakaoNaviClient, req.toClientRequest())
        val response = directionService.createRoute(kakaoRes)

    return ResponseEntity.ok(response)
    }
}

fun getKakaoResponse(client: KakaoNaviClient,@RequestBody req: ClientRequest): KakaoDirectionsResponse =
    client.oneToOneApi(req)