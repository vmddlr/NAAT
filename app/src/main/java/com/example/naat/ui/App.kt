package com.example.naat.ui

import android.app.Application
import com.example.naat.data.util.SharedPreference
import dagger.hilt.android.HiltAndroidApp

val preference: SharedPreference by lazy { App.prefs!! }

@HiltAndroidApp
class App : Application() {

    companion object {
        var prefs: SharedPreference? = null
    }

    override fun onCreate() {
        super.onCreate()

        prefs = SharedPreference(this.applicationContext)
    }
}