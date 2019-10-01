package com.safeboda.commons.analytics.provider.amplitude

import android.app.Application
import com.amplitude.api.Amplitude
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.provider.AnalyticsProvider
import org.json.JSONObject

class AmplitudeAnalyticsProvider(app: Application, apiKey: String) : AnalyticsProvider {
    private val amplitude = Amplitude.getInstance()
        .initialize(app.baseContext, apiKey)
        .enableForegroundTracking(app)

    companion object {
        private const val JSON_USER_MAIL = "mail"
        private const val JSON_USER_AGE = "age"
    }

    override fun setUser(user: AnalyticsUser) {
        val jsonObject = JSONObject().apply {
            put(JSON_USER_MAIL, user.email)
            put(JSON_USER_AGE, user.id)
        }

        amplitude.setUserProperties(jsonObject)
    }

    override fun clearUser() {
        amplitude.clearUserProperties()
    }

    override fun setUserLogged() {
        amplitude.
    }

    override fun setUserNotLogged() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun track(event: AnalyticsEvent) {
        val jsonObject = JSONObject()

        event.getProperties().forEach {
            jsonObject.put(it.key.name, it.value.getSafeValue())
        }

        amplitude.logEvent(event.name, jsonObject)
    }
}