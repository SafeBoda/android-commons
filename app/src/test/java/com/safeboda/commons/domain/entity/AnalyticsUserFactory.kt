package com.safeboda.commons.domain.entity

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