package com.hshim.pubg_support.feature.mission.scheduler

import com.hshim.pubg_support.feature.mission.sse.MissionSSEConnector
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MissionScheduler(
    private val missionSSEConnector: MissionSSEConnector,
) {
    @Transactional
    @Scheduled(fixedDelay = 5 * 60 * 1000)
    fun buildMissionByOpenAI() {
        // 연결된 Emitter가 있을 경우에만 생성
        if (missionSSEConnector.existsMissionEmitter()) {
            println("미션 생성됨")
        }
    }
}