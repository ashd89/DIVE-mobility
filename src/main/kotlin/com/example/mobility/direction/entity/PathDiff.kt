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
import org.locationtech.jts.geom.Point

@Entity
@Table(name = "path_diff")
class PathDiff(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    // 두 경로 비교 (동일 테이블 FK 두 개)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pathes_id1", nullable = false)
    val path1: Pathes,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pathes_id2", nullable = false)
    val path2: Pathes,

    // 비교 기준 포인트(예: 분기점)
    @Column(name = "before_diff", columnDefinition = "geometry(Point,4326)", nullable = false)
    val beforeDiff: Point,

    @Column(name = "after_diff", columnDefinition = "geometry(Point,4326)", nullable = false)
    val afterDiff: Point,

    @Column(name = "path1_coords", columnDefinition = "geometry(LineString,4326)")
    val path1Coords: LineString? = null,

    @Column(name = "path2_coords", columnDefinition = "geometry(LineString,4326)")
    val path2Coords: LineString? = null,

    @Column(name = "diff_time")
    val diffTime: Int? = null,          // 증가/감소 시간(초)

    @Column(name = "diff_stress")
    val diffStress: Double? = null,     // 증가/감소 스트레스 지수

    @Column(name = "selected_path")
    val selectedPath: Int? = null       // 1(origin) or 2(new)
) : BaseEntity()