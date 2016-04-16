package com.arnaudpiroelle.conference.core.database.utils

import android.database.Cursor

fun Cursor.getInt(columnName: String): Int? {
    return getInt(getColumnIndex(columnName))
}

fun Cursor.getString(columnName: String): String? {
    return getString(getColumnIndex(columnName))
}

fun Cursor.getBoolean(columnName: String): Boolean? {
    return getInt(columnName) == 1
}

fun Cursor.getLong(columnName: String): Long? {
    return getLong(getColumnIndex(columnName))
}
