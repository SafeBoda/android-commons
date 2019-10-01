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

    override fun setUser(user: AnalyticsUser) {
        amplitude.userId = user.id.toString()
        amplitude.setUserProperties()
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}