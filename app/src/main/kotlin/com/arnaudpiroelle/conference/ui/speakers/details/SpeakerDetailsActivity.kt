package com.arnaudpiroelle.conference.ui.speakers.details

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.core.utils.Intents
import com.arnaudpiroelle.conference.core.utils.ProtocolConstants
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.ui.core.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_speaker_details.*
import javax.inject.Inject


class SpeakerDetailsActivity : BaseActivity(), SpeakerDetailsContract.View {

    @Inject lateinit var speakerDao: SpeakerDao

    val userActionsListener: SpeakerDetailsContract.UserActionsListener by lazy { SpeakerDetailsPresenter(this, speakerDao) }
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

        Picasso.with(this).load(speaker.thumbnailUrl).into(speaker_image)
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

}