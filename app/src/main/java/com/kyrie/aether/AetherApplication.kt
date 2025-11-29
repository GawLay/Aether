package com.kyrie.aether

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import java.io.PrintStream

@HiltAndroidApp
class AetherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        System.setOut(object : PrintStream(System.out) {
            override fun println(x: String?) {
                Log.d("PureKotlin", x ?: "null")
            }
        })
    }
}