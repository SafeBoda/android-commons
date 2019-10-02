package com.safeboda.commons.analytics.provider

import android.app.Application
import android.content.Context
import com.safeboda.commons.analytics.provider.amplitude.AmplitudeAnalyticsProvider
import com.safeboda.commons.analytics.provider.google.GoogleAnalyticsProvider

sealed class ProviderConfigurator {

    class AmplitudeConfigurator(
        val app: Application,
        val apiKey: String
    ) : ProviderConfigurator()

    class GoogleConfigurator(
        val context: Context
    ) : ProviderConfigurator()

    internal operator fun invoke(): AnalyticsProvider = when (this) {
        is AmplitudeConfigurator -> AmplitudeAnalyticsProvider(app, apiKey)
        is GoogleConfigurator -> GoogleAnalyticsProvider(context)
    }

}