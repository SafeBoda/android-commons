package com.safeboda.commons.domain.entity

interface AnalyticsEvent {

    val name: String

    fun getProperties(): Map<AnalyticsProperty, AnalyticsValue<out Any>>

}

inline class AnalyticsProperty(val name: String)

class AnalyticsValue<T>(
    val value: T? = null,
    val values: List<T>? = null
) {

    fun getSafeValue() = value ?: values!!

}