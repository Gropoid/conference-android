package com.arnaudpiroelle.conference.model.rel


data class SessionSpeaker(var sessionId: String, var speakerId: String) {
    companion object {
        const val TABLE_NAME = "session_speaker"

        const val COL_SESSION = "session"
        const val COL_SPEAKER = "speaker"
    }
}