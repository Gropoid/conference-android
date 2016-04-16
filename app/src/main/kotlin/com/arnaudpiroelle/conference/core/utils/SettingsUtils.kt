package com.arnaudpiroelle.conference.core.utils

import android.content.Context
import android.preference.PreferenceManager
import com.arnaudpiroelle.conference.BuildConfig
import java.util.*


object SettingsUtils {
    const val PREF_LOCAL_TIMES = "pref_local_times"

    fun getDisplayTimeZone(context: Context): TimeZone {
        val defaultTz = TimeZone.getDefault()
        return if (isUsingLocalTime(context) && defaultTz != null)
            defaultTz
        else
            TimeZone.getTimeZone(BuildConfig.CONFERENCE_TIMEZONE)
    }

    fun isUsingLocalTime(context: Context): Boolean {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        return sp.getBoolean(PREF_LOCAL_TIMES, false)
    }
}