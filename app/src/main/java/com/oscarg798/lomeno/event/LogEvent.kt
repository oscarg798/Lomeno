package com.oscarg798.lomeno.event

interface LogEvent {

    val name: String
    val properties: Map<String, String>

    fun isSourceSupported(logSource: LogSource): Boolean
}