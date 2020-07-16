package com.oscarg798.lomeno.network

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkTrackingEvent(val eventName: String)