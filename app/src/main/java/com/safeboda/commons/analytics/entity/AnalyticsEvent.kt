package com.safeboda.commons.analytics.entity

interface AnalyticsEvent {
    val name: String

    fun getProperties(): Map<String, AnalyticsValue<out Any>>
}

