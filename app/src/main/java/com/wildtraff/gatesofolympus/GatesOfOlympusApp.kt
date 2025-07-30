package com.wildtraff.gatesofolympus

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GatesOfOlympusApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("App", "GatesOfOlympusApp created") // ← Перевір у логах!
    }
}