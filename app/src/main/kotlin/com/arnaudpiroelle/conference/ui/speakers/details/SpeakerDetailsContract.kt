package com.arnaudpiroelle.conference.ui.speakers.details

import com.arnaudpiroelle.conference.model.Speaker

interface SpeakerDetailsContract {
    interface View {
        fun showSpeaker(speaker: Speaker)
        fun refreshMenu(speaker: Speaker)

        fun displayGithub()
        fun displayTwitter()
        fun displayWebsite()
    }

    interface UserActionsListener {
        fun subscribe(speakerId: String)
        fun unsubscribe()

        fun selectOptionItem(itemId: Int)
    }
}
