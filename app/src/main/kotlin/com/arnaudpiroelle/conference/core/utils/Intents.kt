package com.arnaudpiroelle.conference.core.utils

import android.content.Context
import android.content.Intent
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants.EXTRA_SESSION_ID
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants.EXTRA_SPEAKER_ID
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants.EXTRA_TAG_ID
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.ui.sessions.details.SessionDetailsActivity
import com.arnaudpiroelle.conference.ui.speakers.details.SpeakerDetailsActivity
import com.arnaudpiroelle.conference.ui.sessions.listing.SessionListingActivity


object Intents {
    fun createSpeakerDetails(context: Context, speaker: Speaker): Intent {
        return Intent(context, SpeakerDetailsActivity::class.java)
                .putExtra(EXTRA_SPEAKER_ID, speaker.id)
    }

    fun createSessionListing(context: Context, tag: Tag): Intent {
        return Intent(context, SessionListingActivity::class.java)
                .putExtra(EXTRA_TAG_ID, tag.id)
    }

    fun createSessionDetails(context: Context, session: Session): Intent {
        return Intent(context, SessionDetailsActivity::class.java)
                .putExtra(EXTRA_SESSION_ID, session.id)
    }

    fun createExternalLink(context: Context): CustomTabsIntent {
        return CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .build();
    }
}