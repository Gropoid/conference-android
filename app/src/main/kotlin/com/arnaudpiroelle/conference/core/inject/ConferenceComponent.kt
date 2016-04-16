package com.arnaudpiroelle.conference.core.inject

import com.arnaudpiroelle.conference.core.inject.module.ApiModule
import com.arnaudpiroelle.conference.core.inject.module.ApplicationModule
import com.arnaudpiroelle.conference.core.inject.module.DatabaseModule
import com.arnaudpiroelle.conference.services.SyncService
import com.arnaudpiroelle.conference.ui.explore.ExploreFragment
import com.arnaudpiroelle.conference.ui.sessions.details.SessionDetailsActivity
import com.arnaudpiroelle.conference.ui.sessions.listing.SessionListingActivity
import com.arnaudpiroelle.conference.ui.speakers.details.SpeakerDetailsActivity
import com.arnaudpiroelle.conference.ui.speakers.listing.SpeakersListingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        DatabaseModule::class,
        ApiModule::class)
)
interface ConferenceComponent {
    fun inject(syncService: SyncService)
    fun inject(speakersListingFragment: SpeakersListingFragment)
    fun inject(speakerDetailsActivity: SpeakerDetailsActivity)
    fun inject(exploreFragment: ExploreFragment)
    fun inject(sessionListingActivity: SessionListingActivity)
    fun inject(sessionDetailsActivity: SessionDetailsActivity)
}
