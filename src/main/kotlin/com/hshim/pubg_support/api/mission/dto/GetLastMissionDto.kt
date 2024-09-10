package com.hshim.pubg_support.api.mission.dto

import com.hshim.pubg_support.database.entity.misson.Mission

class GetLastMissionDto {
    class Res(
        val content: String?,
    ) {
        constructor(mission: Mission?): this (
            content = mission?.content
        )
    }
}