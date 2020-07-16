package com.oscarg798.lomeno.interceptor.requestprocessor

import okhttp3.Request

interface RequestProcessor {

    fun getTrackingEvent(request: Request): String?
}