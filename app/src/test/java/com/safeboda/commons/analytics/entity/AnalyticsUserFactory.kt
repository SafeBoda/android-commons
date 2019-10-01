package com.safeboda.commons.analytics.entity

import com.safeboda.commons.analytics.entity.AnalyticsUser

class AnalyticsUserFactory {

    companion object {
        fun providesAnalyticsUser(
            id: Long = 1234,
            email: String = "user@safeboda.com"
        ) = AnalyticsUser(
            id = id,
            email = email
        )
    }

}