package com.safeboda.commons.analytics.provider

import android.app.Activity
import android.app.Application
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.safeboda.commons.analytics.entity.AnalyticsEvent
import com.safeboda.commons.analytics.entity.AnalyticsUser

class AppsFlyerProvider(
    val app: Application,
    apiKey: String
) : AnalyticsProvider {

    private val conversionDataListener: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            data?.let { cvData ->
                cvData.map {
                    //Log.i(LOG_TAG, "conversion_attribute:  ${it.key} = ${it.value}")
                }
            }
        }

        override fun onConversionDataFail(error: String?) {
            //Log.e(LOG_TAG, "error onAttributionFailure :  $error")
        }

        override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
            data?.map {
                //Log.d(LOG_TAG, "onAppOpen_attribute: ${it.key} = ${it.value}")
            }
        }

        override fun onAttributionFailure(error: String?) {
            //Log.e(LOG_TAG, "error onAttributionFailure :  $error")
        }

    }

    private val appsFlyerInstance = AppsFlyerLib.getInstance()
        .init(apiKey, conversionDataListener, app.baseContext)
    //.startTracking(app.baseContext)

    override fun setUser(user: AnalyticsUser) {
        AppsFlyerLib.getInstance().setCustomerIdAndTrack(user.id.toString(), app.baseContext)
        // NO-OP
    }

    override fun clearUser(user: AnalyticsUser) {
        // NO-OP
    }

    override fun setUserLogged() {
        // NO-OP
    }

    override fun setUserNotLogged() {
        // NO-OP
    }

    override fun track(event: AnalyticsEvent) {
        AppsFlyerLib.getInstance().trackEvent(app.baseContext, event.name, event.toMap())
    }

    override fun trackScreen(activity: Activity?, screenName: String, overrideScreenClass: String?) {
        // NO-OP
    }

    override fun setFcmToken(token: String) {
        // NO-OP
    }

}