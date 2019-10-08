package com.safeboda.commons.analytics.entity

import com.safeboda.commons.analytics.extensions.mapToBundle
import com.safeboda.commons.analytics.extensions.mapToJsonObject

abstract class AnalyticsUser(
    val id: Long,
    val identifier: String
) {

    private val properties: MutableMap<String, Any> = mutableMapOf(
        USER_ID to id,
        USER_IDENTIFIER to identifier
    )

    fun getProperties(): Map<String, Any> = properties

    fun addProperty(key: String, value: Any) {
        properties[key] = value
    }

    fun toBundle() = properties.mapToBundle()

    fun toJsonObject() = properties.mapToJsonObject()

}