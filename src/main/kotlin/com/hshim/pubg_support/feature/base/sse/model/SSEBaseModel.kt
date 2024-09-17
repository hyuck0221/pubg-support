package com.hshim.pubg_support.feature.base.sse.model

import com.fasterxml.jackson.annotation.JsonIgnore

open class SSEBaseModel (
    @JsonIgnore
    val eventName: String,
)