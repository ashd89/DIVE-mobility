package com.example.mobility.client.dto.res

import com.example.mobility.direction.entity.Routes
import com.example.mobility.global.dto.Coord
import com.example.mobility.global.dto.toCoord

data class CreateDirectionResponse(
    val origin: Coord?,
    val destination: Coord?,
    val waypoints: List<Coord> = emptyList(),
) {
    companion object {
        fun fromEntity(routes: Routes): CreateDirectionResponse {
            return CreateDirectionResponse(
                origin = routes.originCoord.toCoord(),
                destination = routes.destinationCoord.toCoord(),
                waypoints = routes.waypoints
                    .mapNotNull { it.coord.toCoord() }
            )
        }
    }
}