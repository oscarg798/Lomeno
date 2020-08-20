package com.oscarg798.lomeno.logger

import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.event.LogSource
import com.oscarg798.lomeno.exceptions.LogSourceNotSupportedException

class Lomeno(private val childLoggers: Map<LogSource, Collection<Logger>>) : Logger {

    override fun log(logEvent: LogEvent) {
        val eventSupporters = childLoggers
            .filterKeys { source ->
                logEvent.isSourceSupported(source)
            }

        if (eventSupporters.isEmpty()) {
            throw LogSourceNotSupportedException(logEvent.name)
        }

        eventSupporters.values.forEach { loggerList ->
            loggerList.forEach { logger ->
                logger.log(logEvent)
            }
        }
    }

    override fun identify(id: String) {
        childLoggers.values.forEach { loggers ->
            loggers.forEach { it.identify(id) }
        }
    }

    override fun flush() {
        childLoggers.values.forEach { loggers ->
            loggers.forEach { it.flush() }
        }
    }
}