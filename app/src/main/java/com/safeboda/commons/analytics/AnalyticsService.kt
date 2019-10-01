package com.safeboda.commons.analytics

import androidx.annotation.VisibleForTesting
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.provider.AnalyticsProvider

class AnalyticsService(
    analyticsProviders: List<AnalyticsProvider>
) : AnalyticsProvider {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val providerList: List<AnalyticsProvider> = analyticsProviders

    override fun setUser(user: AnalyticsUser) {
        providerList.forEach { provider -> provider.setUser(user) }
    }

    override fun clearUser() {
        providerList.forEach { provider -> provider.clearUser() }
    }

    override fun setUserLogged() {
        providerList.forEach { provider -> provider.setUserLogged() }
    }

    override fun setUserNotLogged() {
        providerList.forEach { provider -> provider.setUserNotLogged() }
    }

    override fun track(event: AnalyticsEvent) {
        providerList.forEach { provider -> provider.track(event) }
    }

}