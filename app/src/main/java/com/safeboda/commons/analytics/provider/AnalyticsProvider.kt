package com.safeboda.commons.analytics.provider

import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser

internal interface AnalyticsProvider {

    fun setUser(user: AnalyticsUser)

    fun clearUser()

    fun setUserLogged()

    fun setUserNotLogged()

    fun track(event: AnalyticsEvent)

}