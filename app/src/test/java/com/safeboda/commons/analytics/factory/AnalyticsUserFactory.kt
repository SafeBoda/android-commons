package com.safeboda.commons.analytics.factory

import com.safeboda.commons.analytics.entity.AnalyticsUser

class AnalyticsUserFactory {

    companion object {
        fun providesAnalyticsUser(
            id: Long = 1234,
            phoneNumber: String = "+34666111222"
        ) = FakeAnalyticsUser(
            id = id,
            identifier = phoneNumber
        )
    }

    class FakeAnalyticsUser(id: Long, identifier: String) :
        AnalyticsUser(id = id, identifier = identifier)

}