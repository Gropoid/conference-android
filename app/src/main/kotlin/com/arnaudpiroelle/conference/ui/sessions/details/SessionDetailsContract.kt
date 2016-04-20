package com.arnaudpiroelle.conference.ui.sessions.details

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Tag


interface SessionDetailsContract {
    interface View {
        fun showSession(session: Session)

        fun cleanTags()
        fun addTag(tag: Tag)

        fun cleanSpeakers()
        fun addSpeaker(speaker: Speaker)

    }

    interface UserActionsListener {
        fun subscribe(sessionId: String)
        fun unsubscribe()
    }
}