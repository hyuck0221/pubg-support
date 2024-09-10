package com.hshim.pubg_support.database.repository.mission

import com.hshim.pubg_support.database.entity.misson.Mission
import org.springframework.data.jpa.repository.JpaRepository

interface MissionRepository: JpaRepository<Mission, String> {
    fun findTopByOrderByCreateDateDesc(): Mission?
}