package com.safeboda.commons.analytics.entity

import com.safeboda.commons.analytics.factory.AnalyticsValueFactory.Companion.providesAnalyticsValue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

private const val USER = "USER"

@RunWith(MockitoJUnitRunner::class)
class AnalyticsValueUnitTest {

    @Test
    fun `getSafeValue should return a list value if value is null`() {
        val analyticsValue = providesAnalyticsValue(
            value = null,
            values = listOf(
                USER,
                USER
            )
        )

        assert(analyticsValue.getSafeValue() is List<*>)
    }

    @Test
    fun `getSafeValue should return a value if list of values is null`() {
        val analyticsValue = providesAnalyticsValue(
            value = USER,
            values = null
        )

        assert(analyticsValue.getSafeValue() is String)
    }

}