package com.arnaudpiroelle.conference.ui.sessions.details

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker


interface SessionDetailsContract {
    interface View {
        fun showSession(session: Session)
        fun showSpeakers(speaker: List<Speaker>)

    }

    interface UserActionsListener {
        fun subscribe(sessionId: String)
        fun unsubscribe()
    }
}