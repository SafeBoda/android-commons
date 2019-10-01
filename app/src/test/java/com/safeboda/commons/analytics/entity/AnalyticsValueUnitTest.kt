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
        val value = providesAnalyticsValue(
            value = null,
            values = listOf(
                USER,
                USER
            )
        )

        assert(value.getSafeValue() is List<*>)
    }

    @Test
    fun `getSafeValue should return a value if list of values is null`() {
        val value = providesAnalyticsValue(
            value = USER,
            values = null
        )

        assert(value.getSafeValue() is String)
    }

    @Test(expected = Exception::class)
    fun `getSafeValue should return a exception if both values are null`() {
        val value = providesAnalyticsValue(
            value = null,
            values = null
        )

        value.getSafeValue()
    }

}