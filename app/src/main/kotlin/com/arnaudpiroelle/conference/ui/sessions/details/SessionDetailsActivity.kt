package com.arnaudpiroelle.conference.ui.sessions.details

import android.os.Bundle
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.ui.core.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_session_details.*
import javax.inject.Inject

class SessionDetailsActivity : BaseActivity(), SessionDetailsContract.View {

    @Inject lateinit var sessionDao: SessionDao
    @Inject lateinit var speakerDao: SpeakerDao

    val sessionId: String by lazy { intent.getStringExtra(ProtocolConstants.EXTRA_SESSION_ID) }
    val userActionsListener: SessionDetailsContract.UserActionsListener  by lazy { SessionDetailsPresenter(this, sessionDao, speakerDao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_session_details)

        GRAPH.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onStart() {
        super.onStart()

        userActionsListener.subscribe(sessionId)
    }

    override fun onStop() {
        super.onStop()

        userActionsListener.unsubscribe()
    }

    override fun showSession(session: Session) {
        session_title.text = session.title

        Picasso.with(this).load(session.photoUrl).into(session_image)
    }

    override fun showSpeakers(speaker: List<Speaker>) {

    }

}