package com.safeboda.commons.analytics.provider

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser

class GoogleAnalyticsProvider(
    context: Context
) : AnalyticsProvider {

    private var firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun setUser(user: AnalyticsUser) {
        firebaseAnalytics.setUserId(user.id.toString())
        firebaseAnalytics.setUserProperty("user_identifier", user.userIdentifier)
    }

    override fun clearUser() {
        firebaseAnalytics.setUserId(null)
        firebaseAnalytics.setUserProperty("user_identifier", null)
    }

    override fun setUserLogged() {
        setLoginEvent(true)
    }

    override fun setUserNotLogged() {
        setLoginEvent(false)
    }

    private fun setLoginEvent(isLogged: Boolean) {
        val bundle = Bundle().apply {
            putBoolean("is_user_logged_in", isLogged)
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    override fun track(event: AnalyticsEvent) {
        val bundle = Bundle()

        for ((propertyName, value) in event.getProperties()) {
            when (value.getSafeValue()) {
                is List<*> -> (value.getSafeValue() as List<*>).forEach { propertyValue ->
                    bundle.putString(propertyName, propertyValue.toString())
                }
                is Boolean -> bundle.putBoolean(propertyName, value.getSafeValue() as Boolean)
                is Long -> bundle.putLong(propertyName, value.getSafeValue() as Long)
                is Int -> bundle.putInt(propertyName, value.getSafeValue() as Int)
                is Float -> bundle.putFloat(propertyName, value.getSafeValue() as Float)
                is Double -> bundle.putDouble(propertyName, value.getSafeValue() as Double)
                else -> bundle.putString(propertyName, value.getSafeValue().toString())
            }
        }

        firebaseAnalytics.logEvent(event.name, bundle)
    }

}