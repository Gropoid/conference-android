package com.arnaudpiroelle.conference.ui.speakers.listing

import com.arnaudpiroelle.conference.model.Speaker


interface SpeakersListingContract {
    interface View {
        fun showSpeakers(speakers: List<Speaker>)
        fun displaySpeakerDetails(speaker: Speaker)
    }

    interface UserActionsListener {
        fun subscribe()
        fun unsubscribe()
        fun openSpeakerDetails(speaker: Speaker)

    }
}