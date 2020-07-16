package com.oscarg798.lomeno.event

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkTrackingEvent(val eventName: String)