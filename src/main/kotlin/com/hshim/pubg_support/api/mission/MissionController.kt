package com.hshim.pubg_support.api.mission

import com.hshim.pubg_support.api.mission.dto.BuildMissionDto
import com.hshim.pubg_support.api.mission.dto.GetLastMissionDto
import com.hshim.pubg_support.api.mission.service.MissionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/mission")
@Tag(name = "미션 API")
class MissionController(
    private val missionService: MissionService
) {
    @Operation(summary = "SSE 연결")
    @GetMapping("/connect", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun missionSSEConnect() = missionService.missionSSEConnect()

    @Operation(summary = "최신 미션 보기")
    @GetMapping("/latest")
    fun getLastMission(): GetLastMissionDto.Res {
        return missionService.getLastMission()
    }

    @Operation(summary = "openAI 미션 생성")
    @PostMapping("/build/open-ai")
    fun buildMissionByOpenAI(): BuildMissionDto.Res {
        return missionService.buildMissionByOpenAI()
    }
}