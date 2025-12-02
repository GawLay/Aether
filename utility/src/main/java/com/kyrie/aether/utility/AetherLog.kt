package com.kyrie.aether.utility

import android.util.Log
import java.util.Formatter

object AetherLog {
    private const val MIN_STACK_OFFSET = 3
    private var defaultTag = "AetherLog"
    private var isEnabled = true

    // Enable/disable all logging
    fun disableForProduction() {
        isEnabled = false
    }

    fun enableForDebug() {
        isEnabled = true
    }

    private const val TOP_BORDER = "┌────────────────────────────────────────────"
    private const val LEFT_BORDER = "│ "
    private const val BOTTOM_BORDER = "└────────────────────────────────────────────"

    private const val MAX_LOG_LENGTH = 4000 // Android logcat limit

    // Get call info for log header
    private fun getCallInfo(): String {
        val elements = Thread.currentThread().stackTrace
        val offset = getStackOffset(elements)
        val element = elements[offset]

        return Formatter()
            .format(
                "Thread:%s | %s(%s:%d)",
                Thread.currentThread().name,
                element.methodName,
                element.fileName,
                element.lineNumber,
            ).toString()
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        for (i in MIN_STACK_OFFSET until trace.size) {
            if (trace[i].className != AetherLog::class.java.name) {
                return i
            }
        }
        return MIN_STACK_OFFSET
    }

    //  log method with borders
    private fun log(
        level: Int,
        tag: String,
        message: String?,
    ) {
        if (!isEnabled || message.isNullOrBlank()) return

        Log.println(level, tag, TOP_BORDER)

        Log.println(level, tag, "$LEFT_BORDER${getCallInfo()}")

        // Print the actual message (split if too long)
        var start = 0
        while (start < message.length) {
            val end = minOf(start + MAX_LOG_LENGTH, message.length)
            Log.println(level, tag, "$LEFT_BORDER${message.substring(start, end)}")
            start = end
        }

        // Print bottom border
        Log.println(level, tag, BOTTOM_BORDER)
    }

    // Public log methods - simple interface
    fun v(
        tag: String = defaultTag,
        message: String?,
    ) {
        log(Log.VERBOSE, tag, message)
    }

    fun d(
        tag: String = defaultTag,
        message: String?,
    ) {
        log(Log.DEBUG, tag, message)
    }

    fun i(
        tag: String = defaultTag,
        message: String?,
    ) {
        log(Log.INFO, tag, message)
    }

    fun w(
        tag: String = defaultTag,
        message: String?,
    ) {
        log(Log.WARN, tag, message)
    }

    fun e(
        tag: String = defaultTag,
        message: String?,
    ) {
        log(Log.ERROR, tag, message)
    }

    // Convenience methods with default tag
    fun v(message: String?) = v(defaultTag, message)

    fun d(message: String?) = d(defaultTag, message)

    fun i(message: String?) = i(defaultTag, message)

    fun w(message: String?) = w(defaultTag, message)

    fun e(message: String?) = e(defaultTag, message)
}
