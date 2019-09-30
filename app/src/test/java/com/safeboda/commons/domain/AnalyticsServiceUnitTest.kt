package com.safeboda.commons.domain

import com.nhaarman.mockitokotlin2.verify
import com.safeboda.commons.domain.entity.AnalyticsEventFactory.Companion.providesAnalyticsEvent
import com.safeboda.commons.domain.entity.AnalyticsUserFactory.Companion.providesAnalyticsUser
import com.safeboda.commons.domain.provider.AnalyticsProvider
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

    private var providerList = listOf<AnalyticsProvider>()

    @Before
    fun setUp() {
        analyticsService = AnalyticsService(analyticsProvider)
        providerList = analyticsService.providerList
    }

    @Test
    fun `providerList shouldn't be empty after instance`() {
        assert(providerList.isNotEmpty())
    }

    @Test
    fun `setUser should save the user for each AnalyticsProvider`() {
        val user = providesAnalyticsUser()

        analyticsService.setUser(user)

        providerList.forEach { provider ->
            verify(provider).setUser(user)
        }
    }

    @Test
    fun `setUserLogged should clear the user for each Analytic provider`() {
        analyticsService.clearUser()

        providerList.forEach { provider ->
            verify(provider).clearUser()
        }
    }

    @Test
    fun `setUserLogged should set the user logged for each Analytic provider`() {
        analyticsService.setUserLogged()

        providerList.forEach { provider ->
            verify(provider).setUserLogged()
        }
    }

    @Test
    fun `setUserNotLogged should set the not logged user for each Analytic provider`() {
        analyticsService.setUserNotLogged()

        providerList.forEach { provider ->
            verify(provider).setUserNotLogged()
        }
    }

    @Test
    fun `track should track the AnalyticEvent for each Analytic provider`() {
        val event = providesAnalyticsEvent()

        analyticsService.track(event)

        providerList.forEach { provider ->
            verify(provider).track(event)
        }
    }

}