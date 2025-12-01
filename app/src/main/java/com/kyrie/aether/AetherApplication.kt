package com.kyrie.aether

import android.app.Application
import android.util.Log
import com.kyrie.aether.utility.AetherLog
import dagger.hilt.android.HiltAndroidApp
import java.io.PrintStream

@HiltAndroidApp
class AetherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //later check with build config.
        AetherLog.enableForDebug()
        System.setOut(object : PrintStream(System.out) {
            override fun println(x: String?) {
                AetherLog.d("PureKotlin", x ?: "null")
            }
        })
    }
}