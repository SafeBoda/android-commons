package com.safeboda.commons.analytics

import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.provider.AnalyticsProvider

class AnalyticsService(
    private val analyticsProviders: List<AnalyticsProvider>
) : AnalyticsProvider {

    override fun setUser(user: AnalyticsUser) {
        analyticsProviders.forEach { provider -> provider.setUser(user) }
    }

    override fun clearUser() {
        analyticsProviders.forEach { provider -> provider.clearUser() }
    }

    override fun setUserLogged() {
        analyticsProviders.forEach { provider -> provider.setUserLogged() }
    }

    override fun setUserNotLogged() {
        analyticsProviders.forEach { provider -> provider.setUserNotLogged() }
    }

    override fun track(event: AnalyticsEvent) {
        analyticsProviders.forEach { provider -> provider.track(event) }
    }

}