package com.example.mobility.direction.entity

import com.example.mobility.global.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.locationtech.jts.geom.LineString

@Entity
@Table(name = "pathes")
class Pathes(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routes_id", nullable = false)
    val route: Routes,

    val name: String,

    val distance: Int,
    val duration: Int,

    @Column(columnDefinition = "geometry(LineString,4326)", nullable = false)
    val coords: LineString,

    @Column(name = "avg_score")
    val avgScore: Double? = null,

    @Column(name = "is_main", nullable = false)
    val isMain: Boolean = false
) : BaseEntity()