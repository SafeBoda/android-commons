package com.safeboda.commons.analytics.entity

abstract class AnalyticsUser(
    val id: Long?,
    val identifier: String?,
    val firstName: String?,
    val email: String?
) {

    private val properties: MutableMap<String, Any?> = mutableMapOf(
        USER_ID to id,
        USER_IDENTIFIER to identifier,
        USER_NAME to firstName,
        USER_IDENTITY to id,
        USER_EMAIL to email,
        USER_PHONE to identifier
    )

    fun getProperties(): Map<String, Any?> = properties

    fun addProperty(key: String, value: Any?) {
        properties[key] = value
    }

}