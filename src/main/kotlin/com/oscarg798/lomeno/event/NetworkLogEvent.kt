package com.oscarg798.lomeno.event

import com.oscarg798.lomeno.models.NetworkServiceInformation
import com.oscarg798.lomeno.sources.NetworkLogSource

open class NetworkLogEvent(
    override val name: String,
    networkServiceInformation: NetworkServiceInformation
) : LogEvent {

    override val properties: Map<String, String> = mapOf<String, String>(
        (PATH_KEY to networkServiceInformation.path),
        (SCHEMA_KEY to networkServiceInformation.schema),
        (METHOD_KEY to networkServiceInformation.method),
        (SUCESS_KEY to networkServiceInformation.isSuccess.toString()),
        (RESPONSE_CODE to networkServiceInformation.responseCode.toString())
    )

    override fun isSourceSupported(logSource: LogSource): Boolean = logSource is NetworkLogSource
}

private const val SCHEMA_KEY = "SCHEMA"
private const val PATH_KEY = "PATH"
private const val METHOD_KEY = "METHOD"
private const val SUCESS_KEY = "IS_SUCCESS"
private const val RESPONSE_CODE = "RESPONSE_CODE"