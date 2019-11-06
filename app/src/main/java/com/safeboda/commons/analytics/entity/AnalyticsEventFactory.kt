package com.safeboda.commons.analytics.entity

class AnalyticsEventFactory(override val name: String) : AnalyticsEvent(name) {

    companion object {
        fun <T : Any?> createAnalyticsEvent(
            name: String,
            properties: List<Pair<String, T>> = listOf()
        ) =
            AnalyticsEventFactory(name).apply {
                properties.forEach {
                    addProperty(it.first, it.second)
                }
            }

        fun <T : Any?> createAnalyticsEventWithListValues(
            name: String,
            pair: Pair<String, List<T>>
        ) =
            AnalyticsEventFactory(name).apply {
                addListOfProperties(pair.first, pair.second)
            }
    }

}