package com.safeboda.commons.analytics.extensions

import android.os.Bundle
import com.safeboda.commons.analytics.entity.AnalyticsValue
import org.json.JSONObject

fun MutableMap<String, AnalyticsValue<out Any?>>.mapToBundle() = Bundle().apply {
    forEach { map ->
        when (val safeValue = map.value.getSafeValue()) {
            is List<*> -> safeValue.forEach { propertyValue ->
                putString(map.key, propertyValue.toString())
            }
            is Boolean -> putBoolean(map.key, safeValue)
            is Long -> putLong(map.key, safeValue)
            is Int -> putInt(map.key, safeValue)
            is Float -> putFloat(map.key, safeValue)
            is Double -> putDouble(map.key, safeValue)
            is String -> putString(map.key, safeValue)
            else -> putString(map.key, safeValue.toString())
        }
    }
}

fun <V : Any?> Map<String, V>.mapToJsonObject() = JSONObject().apply {
    forEach {
        put(it.key, it.value)
    }
}

fun MutableMap<String, AnalyticsValue<out Any?>>.mapToJsonObjectFromAnalyticsValue() = JSONObject().apply {
    forEach { map ->
        put(map.key, map.value.getSafeValue())
    }
}