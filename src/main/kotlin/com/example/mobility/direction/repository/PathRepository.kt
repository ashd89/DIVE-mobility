package com.example.mobility.direction.repository

import com.example.mobility.direction.entity.Pathes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PathRepository : JpaRepository<Pathes, Long> {

}