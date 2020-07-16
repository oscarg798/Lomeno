package com.oscarg798.lomeno.network.mapper

import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.network.event.NetworkServiceInformation

typealias NetworkServiceName = String

interface NetworkLogEventMapper {

    fun map(
        serviceName: NetworkServiceName,
        serviceInformation: NetworkServiceInformation
    ): LogEvent
}