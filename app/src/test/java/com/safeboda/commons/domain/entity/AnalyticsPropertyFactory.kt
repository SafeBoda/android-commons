package com.safeboda.commons.domain.entity

import com.safeboda.commons.analytics.entity.AnalyticsProperty

class AnalyticsPropertyFactory {

    companion object {
        fun providesAnalyticsProperty(
            name: String = "passenger_email"
        ) = AnalyticsProperty(
            name = name
        )
    }

}