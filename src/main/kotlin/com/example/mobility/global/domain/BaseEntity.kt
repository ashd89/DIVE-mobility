package com.example.mobility.global.domain

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {
    @Column(name = "created_at", nullable = false)
    var createdAt: java.time.OffsetDateTime = java.time.OffsetDateTime.now()
}