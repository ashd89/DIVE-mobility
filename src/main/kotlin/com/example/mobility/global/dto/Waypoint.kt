package com.example.mobility.global.dto

data class Waypoint(
    val name: String? = null,
    val x: Double,
    val y: Double
)

fun composeWaypoints(points: List<Waypoint>): String =
    points.joinToString(" | ") {
        if (it.name.isNullOrBlank()) {
            "${it.x},${it.y}"
        } else {
            "${it.x},${it.y},name=${it.name}"
        }
    }

fun composeWaypoint(point: Waypoint): String =
    if (point.name.isNullOrBlank()) {
        "${point.x},${point.y}"
    } else {
        "${point.x},${point.y},name=${point.name}"
    }

