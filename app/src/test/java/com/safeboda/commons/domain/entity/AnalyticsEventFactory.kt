package com.safeboda.commons.domain.entity

import com.safeboda.commons.domain.entity.AnalyticsPropertyFactory.Companion.providesAnalyticsProperty
import com.safeboda.commons.domain.entity.AnalyticsValueFactory.Companion.providesAnalyticsValue

class AnalyticsEventFactory {

    companion object {
        fun providesAnalyticsEvent(
            name: String = "John"
        ) = object : AnalyticsEvent {

            override val name: String = name

            override fun getProperties(): Map<AnalyticsProperty, AnalyticsValue<out Any>> =
                mapOf(providesAnalyticsProperty() to providesAnalyticsValue())

        }
    }

}