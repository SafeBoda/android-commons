package com.safeboda.commons.analytics.extensions

import android.os.Bundle
import org.json.JSONObject

fun <V : Any?> Map<String, V>.mapToBundle() = Bundle().apply {
    forEach {
        when (it.value) {
            is List<*> -> (it.value as List<*>).forEach { propertyValue ->
                putString(it.key, propertyValue.toString())
            }
            is Boolean -> putBoolean(it.key, it.value as Boolean)
            is Long -> putLong(it.key, it.value as Long)
            is Int -> putInt(it.key, it.value as Int)
            is Float -> putFloat(it.key, it.value as Float)
            is Double -> putDouble(it.key, it.value as Double)
            else -> putString(it.key, it.value.toString())
        }
    }
}

fun <V : Any> Map<String, V?>.mapToJsonObject() = JSONObject().apply {
    forEach {
        put(it.key, it.value)
    }
}