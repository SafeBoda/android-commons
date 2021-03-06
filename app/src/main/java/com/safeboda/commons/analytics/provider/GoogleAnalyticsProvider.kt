package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.entity.IS_USER_LOGGED_IN

class GoogleAnalyticsProvider(
    context: Context
) : AnalyticsProvider {

    private var firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun setUser(user: AnalyticsUser) {
        firebaseAnalytics.setUserId(user.id.toString())
        user.getProperties().forEach { (propertyName: String, value: Any?) ->
            firebaseAnalytics.setUserProperty(propertyName, value.toString())
        }
    }

    override fun clearUser(user: AnalyticsUser) {
        firebaseAnalytics.setUserId(null)
        user.getProperties().forEach { (propertyName: String, _: Any?) ->
            firebaseAnalytics.setUserProperty(propertyName, null)
        }
    }

    override fun setUserLogged() {
        setLoginEvent(true)
    }

    override fun setUserNotLogged() {
        setLoginEvent(false)
    }

    override fun track(event: AnalyticsEvent) {
        firebaseAnalytics.logEvent(event.name, event.toBundle())
    }

    private fun setLoginEvent(isLogged: Boolean) {
        val bundle = Bundle().apply {
            putBoolean(IS_USER_LOGGED_IN, isLogged)
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    override fun setFcmToken(token: String) {
        // NO-OP
    }

    override fun trackScreen(activity: Activity?, screenName: String, overrideScreenClass: String?) {
        activity?.let { safeActivity ->
            firebaseAnalytics.setCurrentScreen(safeActivity, screenName, overrideScreenClass)
        }
    }

}