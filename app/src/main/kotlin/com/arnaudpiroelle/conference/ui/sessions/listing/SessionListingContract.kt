package com.arnaudpiroelle.conference.ui.sessions.listing

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Tag


interface SessionListingContract {
    interface View {
        fun showTag(tag: Tag)
        fun showSessions(sessions: List<Session>)
        fun displaySessionDetails(session: Session)
    }

    interface UserActionsListener {
        fun subscribe(tagId: String)
        fun unsubscribe()

        fun openSessionDetails(session: Session)
    }
}