package com.safeboda.commons.analytics.entity

import com.safeboda.commons.analytics.extensions.mapToBundle
import com.safeboda.commons.analytics.extensions.mapToJsonObject

abstract class AnalyticsEvent(
    val name: String
) {

    abstract fun getProperties(): Map<String, AnalyticsValue<out Any>>

    fun toBundle() = getProperties().mapToBundle()

    fun toJsonObject() = getProperties().mapToJsonObject()

}

