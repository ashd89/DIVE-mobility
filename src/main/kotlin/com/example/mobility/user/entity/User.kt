package com.example.mobility.user.entity

import com.example.mobility.direction.entity.Routes
import com.example.mobility.global.domain.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.locationtech.jts.geom.Point

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nickname: String,

    @Column(name = "notion_sick", nullable = false)
    val notionSick: Boolean = false,

    @Column(nullable = false)
    val acrophobia: Boolean = false,

    @Column(columnDefinition = "geometry(Point,4326)", nullable = true)
    val coord: Point? = null
) : BaseEntity() {

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val places: MutableList<UserPlace> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val routes: MutableList<Routes> = mutableListOf()
}