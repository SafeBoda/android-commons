package com.safeboda.commons.analytics.entity

import com.safeboda.commons.analytics.extensions.mapToBundle
import com.safeboda.commons.analytics.extensions.mapToJsonObject

abstract class AnalyticsEvent(
    val name: String
) {

    private val _ldProperties = mutableMapOf<String, AnalyticsValue<out Any>>()

    protected fun <T : Any> addProperty(name: String, value: T) {
        _ldProperties[name] = AnalyticsValue(value = value)
    }

    protected fun <T : Any> addListOfProperties(name: String, values: List<T>) {
        _ldProperties[name] = AnalyticsValue(values = values)
    }

    internal fun toBundle() = _ldProperties.mapToBundle()

    internal fun toJsonObject() = _ldProperties.mapToJsonObject()

}

