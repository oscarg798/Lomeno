package com.oscarg798.lomeno.logger

import com.oscarg798.lomeno.event.LogEvent

interface Logger {

    fun log(logEvent: LogEvent)

    fun identify(id: String)

    fun flush()
}

