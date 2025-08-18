package com.example.mobility.global.dto

import kotlin.math.cos
import kotlin.math.hypot

data class Coord(
    val name: String? = null,
    val x: Double,
    val y: Double
)

fun composeWaypoints(points: List<Coord>): String =
    points.joinToString(" | ") {
        if (it.name.isNullOrBlank()) {
            "${it.x},${it.y}"
        } else {
            "${it.x},${it.y},name=${it.name}"
        }
    }

fun composeWaypoint(point: Coord): String =
    if (point.name.isNullOrBlank()) {
        "${point.x},${point.y}"
    } else {
        "${point.x},${point.y},name=${point.name}"
    }

fun rectangleAroundSegment(
    start: Coord,
    end: Coord,
    halfWidthMeters: Double = 25.0
): List<Coord> {
    // 근사: 위도 1도 ≈ 111.32km, 경도는 위도 cos 스케일
    val meanLatRad = Math.toRadians((start.y + end.y) / 2.0)
    val mPerDegLat = 111_320.0
    val mPerDegLon = 111_320.0 * cos(meanLatRad)

    // 단위 벡터를 위,경도로
    fun enToWaypoint(e: Double, n: Double): Coord =
        Coord(
            x = start.x + (e / mPerDegLon),
            y = start.y + (n / mPerDegLat)
        )

    // x,y 좌표로 start->(0,0) end->(dxE, dxN)
    val dxE = (end.x - start.x) * mPerDegLon
    val dyN = (end.y - start.y) * mPerDegLat

    // meter 거리
    val len = hypot(dxE, dyN)

    // (ux,uy)는 AB의 단위 벡터 & (nx. ny)는 수직 벡터
    val (ux, uy) = if (len > 1e-6) dxE / len to dyN / len else 1.0 to 0.0
    val nx = -uy
    val ny =  ux

    // start->(0,0) end->(dxE, dxN)을 (nx,ny)로 4방향 평행 이동
    val aLeftE  = nx * halfWidthMeters
    val aLeftN  = ny * halfWidthMeters
    val bLeftE  = dxE + nx * halfWidthMeters
    val bLeftN  = dyN + ny * halfWidthMeters
    val aRightE = -nx * halfWidthMeters
    val aRightN = -ny * halfWidthMeters
    val bRightE = dxE - nx * halfWidthMeters
    val bRightN = dyN - ny * halfWidthMeters

    return listOf(
        enToWaypoint(aLeftE,  aLeftN),
        enToWaypoint(bLeftE,  bLeftN),
        enToWaypoint(bRightE, bRightN),
        enToWaypoint(aRightE, aRightN)
    )
}