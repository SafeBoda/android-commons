package com.safeboda.commons.analytics.entity

class AnalyticsValue<T>(
    val value: T? = null,
    val values: List<T>? = null
) {
    fun getSafeValue() = value ?: values
}