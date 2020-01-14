package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.content.Context
import com.clevertap.android.sdk.CleverTapAPI
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsEventFactory
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.entity.IS_USER_LOGGED_IN

class CleverTapAnalyticsProvider(
    context: Context
) : AnalyticsProvider {

    private val cleverTap = CleverTapAPI.getDefaultInstance(context)

    override fun setUser(user: AnalyticsUser) {
        cleverTap?.pushProfile(user.getProperties())
    }

    override fun clearUser(user: AnalyticsUser) {
        cleverTap?.removeValueForKey(user.id.toString())
        cleverTap?.removeValueForKey(user.identifier)
    }

    override fun setUserLogged() {
        setUserSessionStatus(true)
    }

    override fun setUserNotLogged() {
        setUserSessionStatus(false)
    }

    override fun track(event: AnalyticsEvent) {
        cleverTap?.pushEvent(event.name, event.toMap())
    }

    override fun trackScreen(activity: Activity?, screenName: String, overrideScreenClass: String?) {
        activity?.let {
            val event: AnalyticsEvent = AnalyticsEventFactory.createAnalyticsEvent<AnalyticsEvent>(name = screenName)
            track(event)
        }
    }

    private fun setUserSessionStatus(isLoggedIn: Boolean) {
        val profileUpdate = HashMap<String, Any>().apply {
            put(IS_USER_LOGGED_IN, isLoggedIn)
        }
        cleverTap?.pushProfile(profileUpdate)
    }

}