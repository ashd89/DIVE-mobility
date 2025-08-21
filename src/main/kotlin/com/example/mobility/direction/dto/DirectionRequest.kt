package com.example.mobility.direction.dto

import com.example.mobility.client.dto.req.ClientRequest
import com.example.mobility.global.dto.Coord

data class DirectionRequest(
    val origin: Coord, //출발지
    val destination: Coord, //목적지
    val waypoints: List<Coord>, // 경유지
) {
    fun toClientRequest(): ClientRequest {
        return ClientRequest(
            origin = origin,
            destination = destination,
            waypoints = waypoints,
        )
    }
}