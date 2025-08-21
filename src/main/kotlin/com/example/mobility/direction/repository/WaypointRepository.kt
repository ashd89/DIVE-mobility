package com.example.mobility.direction.repository

import com.example.mobility.direction.entity.Waypoints
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WaypointRepository : JpaRepository<Waypoints, Long> {

}