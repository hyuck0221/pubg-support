package com.hshim.pubg_support.feature.mission.sse

import com.hshim.pubg_support.database.entity.misson.Mission
import com.hshim.pubg_support.database.repository.mission.MissionRepository
import com.hshim.pubg_support.feature.base.sse.util.SSEUtil.emitterSend
import com.hshim.pubg_support.feature.mission.sse.model.MissionEmitterEventModel
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.CopyOnWriteArrayList

@Component
class MissionSSEConnector(
    private val missionRepository: MissionRepository
) {
    private val missionEmitterList = CopyOnWriteArrayList<SseEmitter>()

    fun liveMissionConnect(): SseEmitter {
        val emitter = SseEmitter(60 * 1000)
        emitter.onCompletion { missionEmitterList.remove(emitter) }
        emitter.onTimeout { emitter.complete() }
        emitter.onError { emitter.complete() }

        val mission = missionRepository.findTopByOrderByCreateDateDesc()
        val connectInfo = MissionEmitterEventModel.ConnectInfo(mission)
        missionEmitterList.emitterSend(emitter, connectInfo)

        missionEmitterList.add(emitter)
        return emitter
    }

    fun existsMissionEmitter() = missionEmitterList.size > 0

    fun sendNewMission(mission: Mission) {
        val chatModel = MissionEmitterEventModel.NewMissionInfo(mission)
        missionEmitterList.emitterSend(chatModel)
    }
}