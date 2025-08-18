package com.example.mobility.client.dto.res

data class KakaoDirectionsResponse(
    val transId: String? = null,
    val routes: List<Route> = emptyList()
)

data class Route(
    val resultCode: Int,
    val resultMsg: String? = null,
    val summary: Summary,
    val sections: List<Section>
)

data class Summary(
    val origin: Point,
    val destination: Point,
    val waypoints: List<Point> = emptyList(),
    val priority: String,
    val bound: Bound,
    val fare: Fare,
    val distance: Int,
    val duration: Int
)

data class Point(
    val name: String? = null,
    val x: Double,
    val y: Double
)

data class Bound(
    val minX: Double,
    val minY: Double,
    val maxX: Double,
    val maxY: Double
)

data class Fare(
    val taxi: Int, // 필요하면 Long 로 변경
    val toll: Int
)

data class Section(
    val distance: Int,
    val duration: Int,
    val bound: Bound,
    val roads: List<Road>,
//    val guides: List<Guide>
)

data class Road(
    val name: String,
    val distance: Int,
    val duration: Int,
    val trafficSpeed: Double,
    val trafficState: Int,
    val vertexes: List<Double>
)

//todo 카멜케이스 변경 부분 단순 yml 추가로 실패 => 수정 필요

//data class Guide(
//    val name: String?,
//    val x: Double,
//    val y: Double,
//    val distance: Int,
//    val duration: Int,
//    val type: Int,
//    val guidance: String,
//    val roadIndex: Int
//)