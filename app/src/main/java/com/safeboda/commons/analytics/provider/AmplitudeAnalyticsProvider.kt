package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.app.Application
import com.amplitude.api.Amplitude
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsEventFactory
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.entity.IS_USER_LOGGED_IN
import com.safeboda.commons.analytics.extensions.mapToJsonObject
import org.json.JSONObject

class AmplitudeAnalyticsProvider(
    app: Application,
    apiKey: String
) : AnalyticsProvider {

    private val amplitude = Amplitude.getInstance()
        .initialize(app.baseContext, apiKey)
        .enableForegroundTracking(app)

    override fun setUser(user: AnalyticsUser) {
        amplitude.setUserProperties(user.getProperties().mapToJsonObject())
    }

    override fun clearUser(user: AnalyticsUser) {
        amplitude.clearUserProperties()
    }

    override fun setUserLogged() {
        setUserSessionStatus(true)
    }

    override fun setUserNotLogged() {
        setUserSessionStatus(false)
    }

    override fun track(event: AnalyticsEvent) {
        amplitude.logEvent(event.name, event.toJsonObject())
    }

    private fun setUserSessionStatus(isLoggedIn: Boolean) {
        val jsonObject = JSONObject().apply {
            put(IS_USER_LOGGED_IN, isLoggedIn)
        }

        amplitude.setUserProperties(jsonObject)
    }

    override fun setFcmToken(token: String) {
        // NO-OP
    }

    override fun trackScreen(activity: Activity?, screenName: String, overrideScreenClass: String?) {
        activity?.let {
            val event: AnalyticsEvent = AnalyticsEventFactory.createAnalyticsEvent<AnalyticsEvent>(name = screenName)
            track(event)
        }
    }

}