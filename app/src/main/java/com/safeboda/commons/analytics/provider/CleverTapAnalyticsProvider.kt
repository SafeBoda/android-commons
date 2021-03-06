package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.content.Context
import com.clevertap.android.sdk.CleverTapAPI
import com.safeboda.commons.analytics.entity.*

class CleverTapAnalyticsProvider(
    context: Context
) : AnalyticsProvider {

    private val cleverTap = CleverTapAPI.getDefaultInstance(context)

    override fun setUser(user: AnalyticsUser) {
        val profileUpdate: MutableMap<String, Any?> = mutableMapOf(
            USER_NAME to user.name,
            USER_IDENTITY to user.id,
            USER_EMAIL to user.email,
            USER_PHONE to user.identifier
        )
        profileUpdate.putAll(user.getProperties())
        cleverTap?.onUserLogin(profileUpdate)
    }

    override fun clearUser(user: AnalyticsUser) {
        // NO-OP
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

    override fun setFcmToken(token: String) {
        cleverTap?.pushFcmRegistrationId(token, true)
    }

    private fun setUserSessionStatus(isLoggedIn: Boolean) {
        val profileUpdate = HashMap<String, Any>().apply {
            put(IS_USER_LOGGED_IN, isLoggedIn)
        }
        cleverTap?.pushProfile(profileUpdate)
    }

}