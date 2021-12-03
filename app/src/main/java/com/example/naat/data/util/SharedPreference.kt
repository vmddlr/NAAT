package com.example.naat.data.util

import android.content.Context

class SharedPreference(context: Context) {

    private val fileName = Constants.FILE_NAME

    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var userName: String
        get() = prefs.getString(Constants.USER_NAME, "")!!
        set(value) = prefs.edit().putString(Constants.USER_NAME, value).apply()

    var password: String
        get() = prefs.getString(Constants.PASSWORD, "")!!
        set(value) = prefs.edit().putString(Constants.PASSWORD, value).apply()

    var accessToken: String
        get() = prefs.getString(Constants.TOKEN, "")!!
        set(value) = prefs.edit().putString(Constants.TOKEN, value).apply()

    var tokenType: String
        get() = prefs.getString(Constants.TYPE_TOKEN, "")!!
        set(value) = prefs.edit().putString(Constants.TYPE_TOKEN, value).apply()
}