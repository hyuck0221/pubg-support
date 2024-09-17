package com.hshim.pubg_support.api.mission.service

import com.hshim.pubg_support.api.mission.dto.BuildMissionDto
import com.hshim.pubg_support.api.mission.dto.GetLastMissionDto
import com.hshim.pubg_support.database.repository.mission.MissionRepository
import com.hshim.pubg_support.feature.mission.MissionBuilder
import com.hshim.pubg_support.feature.mission.sse.MissionSSEConnector
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MissionService(
    private val missionRepository: MissionRepository,
    private val missionSSEConnector: MissionSSEConnector,
    private val missionBuilder: MissionBuilder,
) {
    fun missionSSEConnect() = missionSSEConnector.liveMissionConnect()

    @Transactional(readOnly = true)
    fun getLastMission(): GetLastMissionDto.Res {
        val mission = missionRepository.findTopByOrderByCreateDateDesc()
        return GetLastMissionDto.Res(mission)
    }

    @Transactional
    fun buildMissionByOpenAI(): BuildMissionDto.Res {
        val mission = missionBuilder.buildByOpenAI()
        return BuildMissionDto.Res(mission.content)
    }
}