package com.oscarg798.lomeno.mapper

import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.event.NetworkLogEvent
import com.oscarg798.lomeno.models.NetworkServiceInformation

class NetworkLogEventMapperImpl :
    NetworkLogEventMapper {

    override fun map(
        serviceName: NetworkServiceName,
        serviceInformation: NetworkServiceInformation
    ): LogEvent = NetworkLogEvent(serviceName, serviceInformation)
}