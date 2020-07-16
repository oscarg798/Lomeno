package com.oscarg798.lomeno.network.requestprocessor

import okhttp3.Request

interface RequestProcessor {

    fun getTrackingEvent(request: Request): String?
}