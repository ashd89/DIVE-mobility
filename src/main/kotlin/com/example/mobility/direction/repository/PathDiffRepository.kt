package com.example.mobility.direction.repository

import com.example.mobility.direction.entity.PathDiff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PathDiffRepository : JpaRepository<PathDiff, Long> {

}