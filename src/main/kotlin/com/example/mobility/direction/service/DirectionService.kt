package com.example.mobility.direction.service

import com.example.mobility.client.dto.res.CreateDirectionResponse
import com.example.mobility.client.dto.res.KakaoDirectionsResponse
import com.example.mobility.user.entity.User
import io.github.oshai.kotlinlogging.KotlinLogging
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.stereotype.Service

@Service
class DirectionService(
    private val geometryFactory: GeometryFactory
) {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    val user = User(
        1,
        nickname = "나",
        notionSick = false,
        acrophobia = false,
        coord = null
    )
    fun createRoute(res: KakaoDirectionsResponse) : CreateDirectionResponse {
        val routesEntity = res.routes[0].summary.toRouteEntity(user, res.transId, geometryFactory)
        val waypointsEntities = res.routes[0].summary.toWaypointEntities(routesEntity, geometryFactory)
        val coords = waypointsEntities.map { it.coord }
        val pathEntity = res.routes[0].sections[0].roads
        return routesEntity.toCreateDirectionResponse(coords)
    }
}