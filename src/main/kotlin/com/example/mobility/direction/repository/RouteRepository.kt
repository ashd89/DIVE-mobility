package com.example.mobility.direction.repository

import com.example.mobility.direction.entity.Routes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RouteRepository : JpaRepository<Routes, Long> {

}