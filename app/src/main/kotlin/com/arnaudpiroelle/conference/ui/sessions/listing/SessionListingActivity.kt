package com.arnaudpiroelle.conference.ui.sessions.listing

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.TagDao
import com.arnaudpiroelle.conference.core.utils.Intents
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.ui.core.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_session_listing.*
import javax.inject.Inject


class SessionListingActivity : BaseActivity(), SessionListingContract.View {

    @Inject lateinit var sessionDao: SessionDao
    @Inject lateinit var tagDao: TagDao
    @Inject lateinit var picasso: Picasso

    val adapter: SessionListingAdapter by lazy { SessionListingAdapter(this, picasso, userActionsListener) }
    val userActionsListener: SessionListingContract.UserActionsListener by lazy { SessionListingPresenter(this, tagDao, sessionDao) }
    val tagId: String by lazy { intent.getStringExtra(ProtocolConstants.EXTRA_TAG_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_session_listing)

        GRAPH.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        session_list.layoutManager = LinearLayoutManager(this)
        session_list.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        userActionsListener.subscribe(tagId)
    }

    override fun onStop() {
        super.onStop()

        userActionsListener.unsubscribe()
    }

    override fun showSessions(sessions: List<Session>) {
        adapter.datas = sessions
        adapter.notifyDataSetChanged()
    }

    override fun showTag(tag: Tag) {
        title = tag.name
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun displaySessionDetails(session: Session) {
        startActivity(Intents.createSessionDetails(this, session))
    }
}