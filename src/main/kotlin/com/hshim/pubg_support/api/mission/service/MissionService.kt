package com.hshim.pubg_support.api.mission.service

import com.hshim.pubg_support.api.mission.dto.GetLastMissionDto
import com.hshim.pubg_support.database.repository.mission.MissionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.CopyOnWriteArrayList

@Service
class MissionService(
    private val missionRepository: MissionRepository
) {
    private val emitterList = CopyOnWriteArrayList<SseEmitter>()

//    fun missionSSEConnect(userId: String): SseEmitter {
//        val emitter = SseEmitter(600 * 1000)
//        emitter.onCompletion { emitterList.remove(emitter) }
//        emitter.onTimeout { emitter.complete() }
//        emitter.onError { emitter.complete() }
//
//        val mission = userRepository.findByIdOrNull(userId)
//            ?: throw GlobalException.NOT_FOUND_USER.exception
//
//        relayChatEmitterMap[emitter] = user.nickName
//        emitterSend(
//            emitter = emitter,
//            eventName = "connect_chat",
//            data = ChatModel.ConnectChatModel(),
//        )
//
//        emitterSend(
//            eventName = "connecting_user_info",
//            data = ChatModel.ConnectingUserInfo(relayChatEmitterMap.values.toList()),
//        )
//        return emitter
//    }

    @Transactional(readOnly = true)
    fun getLastMission(): GetLastMissionDto.Res {
        val mission = missionRepository.findTopByOrderByCreateDateDesc()
        return GetLastMissionDto.Res(mission)
    }
}