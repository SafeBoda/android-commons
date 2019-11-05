package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.entity.IS_USER_LOGGED_IN
import com.safeboda.commons.analytics.entity.USER_IDENTIFIER

open class GoogleAnalyticsProvider(
    context: Context
) : AnalyticsProvider {

    private var firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun setUser(user: AnalyticsUser) {
        firebaseAnalytics.setUserId(user.id.toString())
        setUserProperty(USER_IDENTIFIER, user.identifier)
    }

    override fun clearUser() {
        firebaseAnalytics.setUserId(null)
        setUserProperty(USER_IDENTIFIER, null)
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

    override fun trackScreen(activity: Activity, screenName: String) {
        firebaseAnalytics.setCurrentScreen(activity, screenName, null)
    }

    fun setUserProperty(propertyName: String, property: String?) {
        firebaseAnalytics.setUserProperty(propertyName, property)
    }

}