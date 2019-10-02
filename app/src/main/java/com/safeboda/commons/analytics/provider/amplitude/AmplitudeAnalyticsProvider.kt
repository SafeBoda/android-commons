package com.safeboda.commons.analytics.provider.amplitude

import android.app.Application
import com.amplitude.api.Amplitude
import com.safeboda.commons.analytics.entity.*
import com.safeboda.commons.analytics.provider.AnalyticsProvider
import org.json.JSONObject

class AmplitudeAnalyticsProvider(
    app: Application,
    apiKey: String
) : AnalyticsProvider {

    private val amplitude = Amplitude.getInstance()
        .initialize(app.baseContext, apiKey)
        .enableForegroundTracking(app)

    override fun setUser(user: AnalyticsUser) {
        val jsonObject = JSONObject().apply {
            put(USER_ID, user.id)
            put(USER_IDENTIFIER, user.userIdentifier)
        }

        amplitude.setUserProperties(jsonObject)
    }

    override fun clearUser() {
        amplitude.clearUserProperties()
    }

    override fun setUserLogged() {
        setUserSessionStatus(true)
    }

    override fun setUserNotLogged() {
        setUserSessionStatus(false)
    }

    override fun track(event: AnalyticsEvent) {
        val jsonObject = JSONObject()

        event.getProperties().forEach {
            jsonObject.put(it.key, it.value.getSafeValue())
        }

        amplitude.logEvent(event.name, jsonObject)
    }

    private fun setUserSessionStatus(isLoggedIn: Boolean) {
        val jsonObject = JSONObject().apply {
            put(IS_USER_LOGGED_IN, isLoggedIn)
        }

        amplitude.setUserProperties(jsonObject)
    }

}