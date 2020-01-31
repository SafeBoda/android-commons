# Android Commons [ ![Download](https://api.bintray.com/packages/safeboda/android/android-commons/images/download.svg?version=0.0.5) ](https://bintray.com/safeboda/android/android-commons/0.0.5/link)

The aim of this library is to content the commons services, providers, API clients, ... for both Android applications. This library must not contain anything related with the presentation layer of Android (base activities, base fragments, custom views ...)

## Analytics

The <b>Analytics</b> package is a wrapper of all analytics clients that are currently being used to send user events to all of them at the same time. <br/>

### Simple example of usage

#### Create an instance of `AnalyticsService` and add the clients that you'll like to it:
```kotlin
val analyticsService = AnalyticsService(listOf(GoogleAnalyticsProvider()))
```

#### Create your own AnalyticsUser:
```kotlin
class SafeBodaAnalyticsUser(
    val id: Long,
    val phoneNumber: String,
    val firstName: String,
    val lastName: String,
    override val email: String? = null,
) : AnalyticsUser(
    id = userId,
    identifier = phoneNumber,
    name = "$firstName $lastName",
    email = email
) {

    init {
        addProperty(FIRST_NAME, firstName)
        addProperty(LAST_NAME, lastName)
    }

    companion object {
        private const val FIRST_NAME = "first_name"
        private const val LAST_NAME = "last_name"
    }

}
```

#### Set the user (when logging in):
```kotlin
analyticsService.setUser(safeBodaAnalyticsUser)
```

#### Track Activities:
```kotlin
analyticsService.trackScreen(
    activity = this,
    screenName = SplashAnalyticsEventManager.EVENT_SPLASH_SCREEN,
    overrideScreenClass = null
)
```

#### Track Fragments:
```kotlin
analyticsService.trackScreen(
    activity = activity,
    screenName = SplashAnalyticsEventManager.EVENT_SPLASH_SCREEN,
    overrideScreenClass = this::class.java.simpleName
)
```

#### Create an AnalyticsEvent:
```kotlin
class SplashAnalyticsEventManager {

    companion object {

        fun splashScreenLoginEvent(
            id: String,
            walletId: Int,
            amount: Double
        ): AnalyticsEvent = AnalyticsEventFactory.createAnalyticsEvent(
            name = EVENT_SPLASH_SCREEN_LOGIN,
            properties = listOf(
                PROPERTY_ID to id,
                PROPERTY_WALLET_ID to walletId,
                PROPERTY_AMOUNT to amount
            )
        )

        // region EVENTS
        const val EVENT_SPLASH_SCREEN = "splash_screen"
        private const val BASIC_EVENT_SPLASH_SCREEN = "splash_screen_"
        private const val EVENT_SPLASH_SCREEN_LOGIN = "${BASIC_EVENT_SPLASH_SCREEN}login"
        // endregion
        
        // region PROPERTIES
        private const val PROPERTY_ID = "id"
        private const val PROPERTY_WALLET_ID = "wallet_id"
        private const val PROPERTY_AMOUNT = "amount"
        // endregion
    }

}
```

#### Track an AnalyticsEvent:
```kotlin
analyticsService.track(SplashAnalyticsEventManager.splashScreenTimeout())
```

#### Clear the user (when logging out):
```kotlin
analyticsService.clearUser(safeBodaAnalyticsUser)
```

## Create your own AnalyticsProvider Client

If you will like to use different clients from the already provided by the library (`GoogleAnalyticsProvider` and `AmplitudeAnalyticsProvider`)

```kotlin
class FacebookAnalyticsProvider : AnalyticsProvider {
    override fun setUser(user: AnalyticsUser) {
    	TODO("Facebook Analytics implementation")
    }

    override fun clearUser(user: AnalyticsUser) {
    	TODO("Facebook Analytics implementation")
    }

    override fun setUserLogged() {
    	TODO("Facebook Analytics implementation")
    }

    override fun setUserNotLogged() {
    	TODO("Facebook Analytics implementation")
    }

    override fun track(event: AnalyticsEvent) {
    	TODO("Facebook Analytics implementation")
    }
    
    override fun trackScreen(activity: Activity?, screenName: String, overrideScreenClass: String?) {
        TODO("Facebook Analytics implementation")
    }
}
```

## Utils

### How to upload to bintray

In order to upload a new version to JFrog you will have to follow these steps: 

- Open `local.properties` file and write these two variables: `bintray.user` with your bintray username and `bintray.key` with your bintray api key (You can find it in `bintray.com->Go to your username->Edit Profile->API Key`). <b>Do not use quotes in `local.properties`</b>
- Execute this command in the Android Studio terminal `./gradlew install` and then `./gradlew bintrayUpload`.
