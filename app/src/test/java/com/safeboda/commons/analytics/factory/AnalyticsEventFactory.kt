package com.safeboda.commons.analytics.factory

import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsValue

class AnalyticsEventFactory {

    companion object {
        fun providesAnalyticsEvent(
            phoneNumber: String = "+34662712381"
        ) = TestAnalyticEvent.UnitTestAnalyticEvent(phoneNumber)
    }

    sealed class TestAnalyticEvent(
        name: String,
        open val testValue: String
    ) : AnalyticsEvent(name) {

        class UnitTestAnalyticEvent(
            override val testValue: String
        ) : TestAnalyticEvent(name = "unit_test_event", testValue = testValue) {

            override fun getProperties(): Map<String, AnalyticsValue<out Any>> =
                mapOf("passenger_phone_number" to AnalyticsValue(testValue))

        }

    }

}