package com.hshim.pubg_support.feature.mission

import com.hshim.pubg_support.database.entity.misson.Mission
import com.hshim.pubg_support.database.repository.mission.MissionRepository
import com.hshim.pubg_support.feature.mission.sse.MissionSSEConnector
import org.springframework.ai.chat.model.ChatModel
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class MissionBuilder(
    private val missionRepository: MissionRepository,
    private val chatModel: ChatModel,
    private val missionSSEConnector: MissionSSEConnector,
) {
    @Transactional
    fun build(content: String): Mission {
        val mission = missionRepository.save(Mission(content = content))
        missionSSEConnector.sendNewMission(mission)
        return mission
    }

    @Transactional
    fun buildByOpenAI(): Mission {
        val command = """
배틀그라운드 게임에서 플레이어가 바로 수행할 수 있는 미션 하나를 부여해
미션은 목표와 조건 두가지를 랜덤으로 부여해주고, 진행해야 할 미션을 명확하게 부여해
모든 맵에서 수행할 수 있어야 하고 미션은 창의성이 있어야 하며 보는사람이 재밌어야 해
답변은 수행해야 할 미션에 대해서만 답변해줘.
            """
        val response = chatModel.call(command)
        return build(response)
    }
}