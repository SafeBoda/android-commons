# Android Commons

The aim of this library is to content the commons services, providers, API clients, ... for both Android applications. This library must not contain anything related with the presentation layer of Android (base activities, base fragments, custom views ...)

## Analytics

The <b>Analytics</b> package is a wrapper of all analytics clients that are currently being used to send user events to all of them at the same time. <br/>

### Simple example of usage

Create an `AnalyticsProvider`

```kotlin
class GoogleAnalyticsProvider : AnalyticsProvider {
    fun setUser(user: AnalyticsUser) {
    	TODO("Google Analytics implementation")
    }

    fun clearUser() {
    	TODO("Google Analytics implementation")
    }

    fun setUserLogged() {
    	TODO("Google Analytics implementation")
    }

    fun setUserNotLogged() {
    	TODO("Google Analytics implementation")
    }

    fun track(event: AnalyticsEvent) {
    	TODO("Google Analytics implementation")
    }
}
```

Create an instance of `AnalyticsService` and add `GoogleAnalyticsProvider` to it

```kotlin
val analyticsService = AnalyticsService(listOf(GoogleAnalyticsProvider()))
```


## Utils

### How to upload to bintray

In order to upload a new version to JFrog you will have to execute this command in the Android Studio terminal `./gradlew build bintrayUpload --info`