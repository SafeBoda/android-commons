package com.safeboda.commons.analytics.factory

import com.safeboda.commons.analytics.entity.AnalyticsUser

class AnalyticsUserFactory {

    companion object {
        fun providesAnalyticsUser(
            id: Long = 1234,
            phoneNumber: String = "+34666111222",
            firstName: String = "Kobe",
            email: String = "kobe@safeboda.com"
        ) = FakeAnalyticsUser(
            id = id,
            identifier = phoneNumber,
            firstName = firstName,
            email = email
        )
    }

    class FakeAnalyticsUser(id: Long, identifier: String, firstName: String, email: String) :
        AnalyticsUser(
            id = id,
            identifier = identifier,
            firstName = firstName,
            email = email
        )

}