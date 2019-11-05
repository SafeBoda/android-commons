package com.safeboda.commons.analytics

import android.app.Activity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.safeboda.commons.analytics.factory.TestAnalyticsEventFactory.Companion.providesAnalyticsEvent
import com.safeboda.commons.analytics.factory.AnalyticsUserFactory.Companion.providesAnalyticsUser
import com.safeboda.commons.analytics.provider.AnalyticsProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AnalyticsServiceUnitTest {

    private lateinit var analyticsService: AnalyticsService

    @Mock
    private lateinit var analyticsProvider: AnalyticsProvider

    @Before
    fun setUp() {
        analyticsService = AnalyticsService(listOf(analyticsProvider))
    }

    @Test
    fun `providerList shouldn't be empty after instance`() {
        assert(analyticsService.analyticsProviders.isNotEmpty())
    }

    @Test
    fun `setUser should save the user for each AnalyticsProvider`() {
        val user = providesAnalyticsUser()

        analyticsService.setUser(user)

        analyticsService.analyticsProviders.forEach { provider ->
            verify(provider).setUser(user)
        }
    }

    @Test
    fun `setUserLogged should clear the user for each Analytic provider`() {
        analyticsService.clearUser()

        analyticsService.analyticsProviders.forEach { provider ->
            verify(provider).clearUser()
        }
    }

    @Test
    fun `setUserLogged should set the user logged for each Analytic provider`() {
        analyticsService.setUserLogged()

        analyticsService.analyticsProviders.forEach { provider ->
            verify(provider).setUserLogged()
        }
    }

    @Test
    fun `setUserNotLogged should set the not logged user for each Analytic provider`() {
        analyticsService.setUserNotLogged()

        analyticsService.analyticsProviders.forEach { provider ->
            verify(provider).setUserNotLogged()
        }
    }

    @Test
    fun `track should track the AnalyticEvent for each Analytic provider`() {
        val event = providesAnalyticsEvent()

        analyticsService.track(event)

        analyticsService.analyticsProviders.forEach { provider ->
            verify(provider).track(event)
        }
    }

    @Test
    fun `trackScreen should track the screen for each Analytic provider`() {
        val activity: Activity = mock()
        val screenName = "Home"

        analyticsService.trackScreen(activity, screenName)

        analyticsService.analyticsProviders.forEach { provider ->
            verify(provider).trackScreen(activity, screenName)
        }
    }

}