package com.safeboda.commons.domain.entity

class AnalyticsPropertyFactory {

    companion object {
        fun providesAnalyticsProperty(
            name: String = "passenger_email"
        ) = AnalyticsProperty(
            name = name
        )
    }

}