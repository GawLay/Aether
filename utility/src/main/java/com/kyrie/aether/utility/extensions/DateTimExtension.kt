package com.kyrie.aether.utility.extensions

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatToHourly(): String =
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault()) // "3:00 PM"
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }

fun String.formatToDayName(): String =
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEE", Locale.getDefault()) // "Mon"
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }

fun String.formatToFullDate(): String =
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat =
            SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()) // "Monday, January 15"
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }

fun String.formatToTime(): String =
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH) // "6:30 AM"
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }

fun String.formatToFullDateAndTime(): String =
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val outputFormat =
            SimpleDateFormat("EEEE, MMMM d h:mm a", Locale.getDefault()) // "Monday, January 15" 6:30 AM
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }
