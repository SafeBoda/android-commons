package com.safeboda.commons.analytics.entity

abstract class AnalyticsUser(
    val id: String?,
    val identifier: String?,
    val name: String?,
    open val email: String?,
    location: String? = null
) {

    private val properties: MutableMap<String, Any?> = mutableMapOf(
        USER_ID to id,
        USER_IDENTIFIER to identifier
    )

    init {
        location?.let {
            addProperty(USER_LOCATION, it)
        }
    }

    fun getProperties(): Map<String, Any?> = properties

    fun addProperty(key: String, value: Any?) {
        properties[key] = value
    }

}