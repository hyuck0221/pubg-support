package com.hshim.pubg_support.feature.mission.sse.model

import com.hshim.pubg_support.database.entity.misson.Mission
import com.hshim.pubg_support.feature.base.sse.model.SSEBaseModel

class MissionEmitterEventModel {
    class ConnectInfo(
        val latestMission: String?,
    ) : SSEBaseModel("connect_info") {
        constructor(mission: Mission?) : this(
            latestMission = mission?.content
        )
    }

    class NewMissionInfo(
        val content: String,
    ) : SSEBaseModel("new_mission_info") {
        constructor(mission: Mission) : this(
            content = mission.content
        )
    }
}