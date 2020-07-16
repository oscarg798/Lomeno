package com.oscarg798.lomeno.sources

import com.oscarg798.lomeno.event.LogSource

object NetworkLogSource : LogSource {
    override val name: String = NETWORK_LOG_SOURCE_NAME
}

const val NETWORK_LOG_SOURCE_NAME = "NETWORK_LOG_SOURCE"