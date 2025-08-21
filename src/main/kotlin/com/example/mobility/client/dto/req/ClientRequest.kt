package com.example.mobility.client.dto.req

import com.example.mobility.global.dto.Coord

data class ClientRequest (
    val origin: Coord, //출발지
    val destination: Coord, //목적지
    val waypoints: List<Coord>, // 경유지
    val priority: String? = "TIME", // 최소 시간 경로
    val avoid: String? = null, // 회피 루트
    val roadevent : Int? = 0, // 사고 반영
    val alternatives: Boolean? = true, // 다중 경로
    val road_details: Boolean? = true, // 도로 상세 정보
    val summary : Boolean? = false // 요약이 false야 나머지 추가 정보를 제공
)

