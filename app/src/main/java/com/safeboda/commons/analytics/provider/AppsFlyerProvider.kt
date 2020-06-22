package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.app.Application
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser
import com.safeboda.commons.analytics.entity.IS_USER_LOGGED_IN

private const val LOG_TAG = "AppsFlyerProvider"

class AppsFlyerProvider(
    val app: Application,
    apiKey: String
) : AnalyticsProvider {

    private val conversionDataListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            data?.let { cvData ->
                cvData.map {
                    Log.i(LOG_TAG, "onConversionDataSuccess conversion_attribute:  ${it.key} = ${it.value}")
                }
            }
        }

        override fun onConversionDataFail(error: String?) {
            Log.e(LOG_TAG, "onConversionDataFail error onAttributionFailure :  $error")
        }

        override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
            data?.map {
                Log.d(LOG_TAG, "onAppOpenAttribution onAppOpen_attribute: ${it.key} = ${it.value}")
            }
        }

        override fun onAttributionFailure(error: String?) {
            Log.e(LOG_TAG, "onAttributionFailure error onAttributionFailure :  $error")
        }

    }

    private val appsFlyerInstance = AppsFlyerLib.getInstance()
        .init(apiKey, conversionDataListener, app.baseContext)

    override fun setUser(user: AnalyticsUser) {
        // TODO: SET THE .startTracking(app.baseContext)
        appsFlyerInstance.setCustomerIdAndTrack(user.id.toString(), app.baseContext)
    }

    override fun clearUser(user: AnalyticsUser) {
        // appsFlyerInstance.stopTracking(true, app.baseContext)
    }

    override fun setUserLogged() {
        setLoginEvent(true)
    }

    override fun setUserNotLogged() {
        setLoginEvent(false)
    }

    private fun setLoginEvent(isLogged: Boolean) {
        val profileUpdate = HashMap<String, Any>().apply {
            put(IS_USER_LOGGED_IN, isLogged)
        }
        appsFlyerInstance.trackEvent(app.baseContext, IS_USER_LOGGED_IN, profileUpdate.toMap())
    }

    override fun track(event: AnalyticsEvent) {
        appsFlyerInstance.trackEvent(app.baseContext, event.name, event.toMap())
    }

    override fun trackScreen(activity: Activity?, screenName: String, overrideScreenClass: String?) {
        // NO-OP
    }

    override fun setFcmToken(token: String) {
        // NO-OP
    }

}