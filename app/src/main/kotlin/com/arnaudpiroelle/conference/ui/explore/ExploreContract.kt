package com.arnaudpiroelle.conference.ui.explore

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.ui.explore.model.Group


interface ExploreContract {
    interface View {
        fun showGroups(it: List<Group<*>>)

        fun displaySessions(tag: Tag)
        fun displaySessionDetails(session: Session)

    }

    interface UserActionsListener {
        fun subscribe()
        fun unsubscribe()

        fun openSessionDetails(session: Session)
        fun openTagDetails(tag: Tag)
    }
}