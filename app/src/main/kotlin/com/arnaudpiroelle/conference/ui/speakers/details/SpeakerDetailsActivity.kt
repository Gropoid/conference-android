package com.arnaudpiroelle.conference.ui.speakers.details

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.core.utils.Intents
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.ui.core.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_speaker_details.*
import kotlinx.android.synthetic.main.item_view_session.*
import kotlinx.android.synthetic.main.item_view_session.view.*
import javax.inject.Inject


class SpeakerDetailsActivity : BaseActivity(), SpeakerDetailsContract.View {

    @Inject lateinit var speakerDao: SpeakerDao
    @Inject lateinit var sessionDao: SessionDao

    val userActionsListener: SpeakerDetailsContract.UserActionsListener by lazy { SpeakerDetailsPresenter(this, speakerDao, sessionDao) }
    val speakerId: String by lazy { intent.getStringExtra(ProtocolConstants.EXTRA_SPEAKER_ID) }

    var speaker: Speaker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_speaker_details)

        GRAPH.inject(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        speaker_details_scrollview.scrollViewListener = View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            supportActionBar?.setDisplayShowTitleEnabled(scrollY >= speaker_header.height)

        }
    }

    override fun onStart() {
        super.onStart()

        userActionsListener.subscribe(speakerId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_speaker_details, menu);

        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val githubMenu = menu.findItem(R.id.menu_speaker_github)
        val twitterMenu = menu.findItem(R.id.menu_speaker_twitter)
        val websiteMenu = menu.findItem(R.id.menu_speaker_website)

        if (speaker != null) {
            twitterMenu?.isVisible = !TextUtils.isEmpty(speaker?.twitter)
            githubMenu?.isVisible = !TextUtils.isEmpty(speaker?.github)
            websiteMenu?.isVisible = !TextUtils.isEmpty(speaker?.website)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        userActionsListener.selectOptionItem(item.itemId)

        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()

        userActionsListener.unsubscribe()
    }

    override fun showSpeaker(speaker: Speaker) {
        title = speaker.name

        speaker_name.text = speaker.name
        speaker_company.text = speaker.company
        speaker_description.text = speaker.bio

        if (!TextUtils.isEmpty(speaker.thumbnailUrl)) {
            Picasso.with(this).load(speaker.thumbnailUrl).placeholder(R.drawable.placeholder_speaker).error(R.drawable.placeholder_speaker).into(speaker_image)
        } else {
            Picasso.with(this).load(R.drawable.placeholder_speaker).into(speaker_image)
        }
    }

    override fun refreshMenu(speaker: Speaker) {
        this.speaker = speaker

        supportInvalidateOptionsMenu()
    }

    override fun displayGithub() {
        val intent = Intents.createExternalLink(this)
        intent.launchUrl(this, Uri.parse("https://github.com/${speaker!!.github}"));
    }

    override fun displayTwitter() {
        val intent = Intents.createExternalLink(this)
        intent.launchUrl(this, Uri.parse("https://twitter.com/${speaker!!.twitter}"));
    }

    override fun displayWebsite() {
        val intent = Intents.createExternalLink(this)
        intent.launchUrl(this, Uri.parse(speaker!!.website));
    }

    override fun cleanSessions() {
        speaker_sessions.removeAllViews()
    }

    override fun addSession(session: Session) {
        val sessionView = LayoutInflater.from(this).inflate(R.layout.item_view_session, speaker_sessions, false)

        sessionView.session_title.text = session.title
        sessionView.session_description.text = session.description

        if(!TextUtils.isEmpty(session.photoUrl)){
            Picasso.with(this).load(session.photoUrl).into(sessionView.session_thumbnail)
        }

        speaker_sessions.addView(sessionView)
    }


}