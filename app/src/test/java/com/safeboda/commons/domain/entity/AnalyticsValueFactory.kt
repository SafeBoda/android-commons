package com.safeboda.commons.domain.entity

class AnalyticsValueFactory {

    companion object {
        fun providesAnalyticsValue(
            value: String? = null,
            values: List<String>? = listOf("value")
        ) = AnalyticsValue(
            value = value,
            values = values
        )
    }

}