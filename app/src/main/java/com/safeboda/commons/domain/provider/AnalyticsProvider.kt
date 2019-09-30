package com.safeboda.commons.domain.provider

import com.safeboda.commons.domain.entity.AnalyticsEvent
import com.safeboda.commons.domain.entity.AnalyticsUser

interface AnalyticsProvider {

    fun setUser(user: AnalyticsUser)

    fun clearUser()

    fun setUserLogged()

    fun setUserNotLogged()

    fun track(event: AnalyticsEvent)

}