package com.hshim.pubg_support.feature.base.sse.util

import com.hshim.pubg_support.feature.base.sse.model.SSEBaseModel
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList

object SSEUtil {
    fun CopyOnWriteArrayList<SseEmitter>.emitterSend(
        emitter: SseEmitter,
        data: SSEBaseModel,
    ) {
        try {
            emitter.send(SseEmitter.event().name(data.eventName).data(data))
        } catch (e: IOException) {
            this.remove(emitter)
        }
    }

    fun CopyOnWriteArrayList<SseEmitter>.emitterSend(data: SSEBaseModel) =
        this.forEach { this.emitterSend(it, data) }
}