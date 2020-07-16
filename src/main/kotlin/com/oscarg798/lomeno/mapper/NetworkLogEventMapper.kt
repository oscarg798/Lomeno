package com.oscarg798.lomeno.mapper

import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.models.NetworkServiceInformation

typealias NetworkServiceName = String

interface NetworkLogEventMapper {

    fun map(
        serviceName: NetworkServiceName,
        serviceInformation: NetworkServiceInformation
    ): LogEvent
}