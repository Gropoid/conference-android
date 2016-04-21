package com.arnaudpiroelle.conference.model.rel


data class SessionTag(val sessionId: String, val tagId: String) {
    companion object {
        const val TABLE_NAME = "session_tag"

        const val COL_SESSION = "session"
        const val COL_TAG = "tag"
    }
}