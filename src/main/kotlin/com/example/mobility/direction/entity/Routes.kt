package com.example.mobility.direction.entity

import com.example.mobility.client.dto.res.CreateDirectionResponse
import com.example.mobility.global.domain.BaseEntity
import com.example.mobility.global.dto.Coord
import com.example.mobility.global.dto.toCoord
import com.example.mobility.user.entity.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.locationtech.jts.geom.Point

@Entity
@Table(name = "routes")
class Routes(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "kakao_request_id", nullable = false)
    val kakaoRequestId: String?,

    @Column(name = "origin_coord", columnDefinition = "geometry(Point,4326)", nullable = false)
    val originCoord: Point?,

    @Column(name = "destination_coord", columnDefinition = "geometry(Point,4326)", nullable = false)
    val destinationCoord: Point?,

    @Column(name = "is_main", nullable = false)
    val isMain: Boolean = false
) : BaseEntity() {

    @OneToMany(mappedBy = "route", cascade = [CascadeType.ALL], orphanRemoval = true)
    val paths: MutableList<Pathes> = mutableListOf()

    @OneToMany(mappedBy = "route", cascade = [CascadeType.ALL], orphanRemoval = true)
    val waypoints: MutableList<Waypoints> = mutableListOf()

    fun toCreateDirectionResponse(waypoints: List<Point?>): CreateDirectionResponse =
        CreateDirectionResponse(
            origin = originCoord.toCoord(),
            destination = destinationCoord.toCoord(),
            waypoints = waypoints.map { it.toCoord() } as List<Coord>
        )

}