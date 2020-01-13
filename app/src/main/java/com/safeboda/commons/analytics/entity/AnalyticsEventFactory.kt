package com.safeboda.commons.analytics.entity

import androidx.annotation.Size

data class AnalyticsEventFactory(override val name: String) : AnalyticsEvent(name) {

    companion object {
        fun <T : Any?> createAnalyticsEvent(
            @Size(max = 40) name: String,
            properties: List<Pair<String, T>> = listOf()
        ) = AnalyticsEventFactory(name).apply {
            properties.forEach {
                addProperty(it.first, it.second)
            }
        }

        fun <T : Any?> createAnalyticsEventWithListValues(
            @Size(max = 40) name: String,
            pair: Pair<String, List<T>>
        ) = AnalyticsEventFactory(name).apply {
            addListOfProperties(pair.first, pair.second)
        }
    }

}