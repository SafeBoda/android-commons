package com.safeboda.commons.analytics.entity

import com.safeboda.commons.analytics.extensions.mapToBundle
import com.safeboda.commons.analytics.extensions.mapToJsonObjectFromAnalyticsValue

abstract class AnalyticsEvent(
    open val name: String
) {

    private val _properties = mutableMapOf<String, AnalyticsValue<out Any?>>()

    protected fun <T : Any?> addProperty(name: String, value: T) {
        _properties[name] = AnalyticsValue(value = value)
    }

    protected fun <T : Any?> addListOfProperties(name: String, values: List<T>) {
        _properties[name] = AnalyticsValue(values = values)
    }

    fun toBundle() = _properties.mapToBundle()

    fun toJsonObject() = _properties.mapToJsonObjectFromAnalyticsValue()

    fun toMap() = _properties.toMap()

}

