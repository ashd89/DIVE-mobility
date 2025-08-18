package com.example.mobility.direction.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.locationtech.jts.geom.Point

@Entity
@Table(name = "waypoints")
class Waypoint(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routes_id", nullable = false)
    val route: Routes,

    val name: String,

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    val coord: Point
)