package com.example.mobility.direction.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.locationtech.jts.geom.Polygon

@Entity
@Table(name = "stress_polygon")
class StressPolygon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val score: Double,

    @Column(name = "coords", columnDefinition = "geometry(Polygon,4326)", nullable = false)
    val coords: Polygon,

    val description: String? = null
)