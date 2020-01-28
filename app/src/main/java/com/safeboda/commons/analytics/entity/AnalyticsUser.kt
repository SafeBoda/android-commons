package com.safeboda.commons.analytics.entity

abstract class AnalyticsUser(
    val id: Long?,
    val identifier: String?,
    open val firstName: String?,
    open val email: String?
) {

    private val properties: MutableMap<String, Any?> = mutableMapOf(
        USER_ID to id,
        USER_IDENTIFIER to identifier
    )

    fun getProperties(): Map<String, Any?> = properties

    fun addProperty(key: String, value: Any?) {
        properties[key] = value
    }

}