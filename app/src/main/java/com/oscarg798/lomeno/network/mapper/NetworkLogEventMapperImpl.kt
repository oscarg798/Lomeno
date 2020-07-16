package com.oscarg798.lomeno.network.mapper

import com.oscarg798.lomeno.event.LogEvent
import com.oscarg798.lomeno.network.event.NetworkLogEvent
import com.oscarg798.lomeno.network.event.NetworkServiceInformation
import com.oscarg798.lomeno.network.mapper.NetworkLogEventMapper
import com.oscarg798.lomeno.network.mapper.NetworkServiceName

class NetworkLogEventMapperImpl :
    NetworkLogEventMapper {

    override fun map(
        serviceName: NetworkServiceName,
        serviceInformation: NetworkServiceInformation
    ): LogEvent = NetworkLogEvent(serviceName, serviceInformation)
}