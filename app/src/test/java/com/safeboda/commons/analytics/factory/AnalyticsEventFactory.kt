package com.safeboda.commons.analytics.factory

import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsProperty
import com.safeboda.commons.analytics.entity.AnalyticsValue

class AnalyticsEventFactory {

    companion object {
        fun providesAnalyticsEvent(
            email: String = "manuel@safeboda.com"
        ) = TestAnalyticEvent.UnitTestAnalyticEvent(email)
    }

    sealed class TestAnalyticEvent(
        override val name: String,
        open val testValue: String
    ) : AnalyticsEvent {

        class UnitTestAnalyticEvent(
            override val testValue: String
        ) : TestAnalyticEvent(name = "unit_test_event", testValue = testValue) {

            override fun getProperties(): Map<AnalyticsProperty, AnalyticsValue<out Any>> =
                mapOf(AnalyticsProperty(name = "passenger_email") to AnalyticsValue(testValue))

        }

    }

}