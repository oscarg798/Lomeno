package com.oscarg798.lomeno.logger

import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.event.LogSource
import com.oscarg798.lomeno.exceptions.LogSourceNotSupportedException

class Lomeno(private val childLoggers: Map<LogSource, Logger>) : Logger {

    override fun log(logEvent: LogEvent) {
        val eventSupporters = childLoggers
            .filterKeys { source ->
                logEvent.isSourceSupported(source)
            }

        if (eventSupporters.isEmpty()) {
            throw LogSourceNotSupportedException(logEvent.name)
        }

        eventSupporters.values.forEach { logger ->
            logger.log(logEvent)

        }
    }

    override fun identify(id: String) {
        childLoggers.values.forEach {
            it.identify(id)
        }
    }
}