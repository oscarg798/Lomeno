package com.oscarg798.lomeno.network.requestprocessor

import com.oscarg798.lomeno.network.NetworkTrackingEvent
import okhttp3.Request
import retrofit2.Invocation

class DefaultRequestProcessor :
    RequestProcessor {


    override fun getTrackingEvent(request: Request): String? {
        val invocation = request.tag(Invocation::class.java)

        return invocation?.method()?.getAnnotation(NetworkTrackingEvent::class.java)?.eventName
    }

}