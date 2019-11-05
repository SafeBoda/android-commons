package com.safeboda.commons.analytics.provider

import android.app.Activity
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser

interface AnalyticsProvider {

    fun setUser(user: AnalyticsUser)

    fun clearUser()

    fun setUserLogged()

    fun setUserNotLogged()

    fun track(event: AnalyticsEvent)

    fun trackScreen(activity: Activity, screenName: String)

}