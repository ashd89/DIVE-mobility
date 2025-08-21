package com.example.mobility.direction.service

import com.example.mobility.client.dto.res.CreateDirectionResponse
import com.example.mobility.client.dto.res.KakaoDirectionsResponse
import com.example.mobility.user.entity.User
import com.example.mobility.user.repository.UserRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.stereotype.Service

import com.example.mobility.client.dto.res.Road
import com.example.mobility.direction.entity.Pathes
import com.example.mobility.direction.entity.Routes
import com.example.mobility.direction.repository.PathRepository
import com.example.mobility.direction.repository.RouteRepository
import com.example.mobility.direction.repository.WaypointRepository
import org.locationtech.jts.geom.Coordinate
import kotlin.collections.chunked
import kotlin.collections.component1
import kotlin.collections.component2

@Service
class DirectionService(
    private val geometryFactory: GeometryFactory,
    private val userRepository: UserRepository,
    private val waypointRepository: WaypointRepository,
    private val routeRepository: RouteRepository,
    private val pathRepository: PathRepository,
) {
    companion object {
        private val log = KotlinLogging.logger {}
    }
    fun createRoute(res: KakaoDirectionsResponse): CreateDirectionResponse {
        val user: User = userRepository.findAll().firstOrNull() ?: throw IllegalStateException("User not found")

        val routesEntity = res.routes[0].summary.toRouteEntity(user, res.trans_id, geometryFactory)
        val waypointsEntities = res.routes[0].summary.toWaypointEntities(routesEntity, geometryFactory)
        val coords = waypointsEntities.map { it.coord }
        val pathEntity = (res.routes[0].sections[0].roads).toPathEntity(routesEntity, geometryFactory)

        routeRepository.save(routesEntity)
        waypointRepository.saveAll(waypointsEntities)
        pathRepository.save(pathEntity)

        return routesEntity.toCreateDirectionResponse(coords)
    }

    fun List<Road>.toPathEntity(route: Routes, geometryFactory: GeometryFactory): Pathes {
        val totalDistance = this.sumOf { it.distance }
        val totalDuration = this.sumOf { it.duration }

        val coordinates = this.flatMap { road ->
            road.vertexes.chunked(2).map { (x, y) ->
                geometryFactory.createPoint(Coordinate(x, y)).coordinate
            }
        }.toTypedArray()

        val lineString = geometryFactory.createLineString(coordinates)

        return Pathes(
            route = route,
            distance = totalDistance,
            duration = totalDuration,
            coords = lineString,
            isMain = true
        )
    }
}
