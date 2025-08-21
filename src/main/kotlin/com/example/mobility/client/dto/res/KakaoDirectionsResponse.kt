package com.example.mobility.client.dto.res

import com.example.mobility.direction.entity.Routes
import com.example.mobility.direction.entity.Waypoints
import com.example.mobility.global.dto.Coord
import com.example.mobility.user.entity.User
import org.locationtech.jts.geom.GeometryFactory

data class KakaoDirectionsResponse(
    val trans_id: String? = null,
    val routes: List<Route> = emptyList()
)

data class Route(
    val result_code: Int,
    val result_msg: String? = null,
    val summary: Summary,
    val sections: List<Section>
)

data class Summary(
    val origin: Coord,
    val destination: Coord,
    val waypoints: List<Coord> = emptyList(),
    val priority: String,
//    val bound: Bound,
//    val fare: Fare,
    val distance: Int,
    val duration: Int
) {
    fun toRouteEntity(user: User, kakaoRequestId: String?, geometryFactory: GeometryFactory) : Routes {
        return Routes(
            user = user,
            kakaoRequestId = kakaoRequestId,
            originCoord = origin.toGeoPoint(geometryFactory),
            destinationCoord = destination.toGeoPoint(geometryFactory)
        )
    }
    fun toWaypointEntities(route: Routes, geometryFactory: GeometryFactory): List<Waypoints> {
        return waypoints.map { coord ->
            Waypoints(
                route = route,
                coord = coord.toGeoPoint(geometryFactory),
                name = coord.name
            )
        }
    }
}

//data class Bound(
//    val minX: Double,
//    val minY: Double,
//    val maxX: Double,
//    val maxY: Double
//)
//
//data class Fare(
//    val taxi: Int, // 필요하면 Long 로 변경
//    val toll: Int
//)

data class Section(
    val distance: Int,
    val duration: Int,
//    val bound: Bound,
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