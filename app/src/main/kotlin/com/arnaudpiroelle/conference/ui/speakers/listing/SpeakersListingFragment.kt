package com.arnaudpiroelle.conference.ui.speakers.listing

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.core.utils.Intents.createSpeakerDetails
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.ui.core.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_speakers_listing.*
import javax.inject.Inject

class SpeakersListingFragment : BaseFragment(), SpeakersListingContract.View {

    @Inject lateinit var speakerDao: SpeakerDao
    @Inject lateinit var picasso: Picasso

    val userActionsListener: SpeakersListingContract.UserActionsListener by lazy { SpeakersListingPresenter(this, speakerDao) }
    val adapter by lazy { SpeakersListingAdapter(activity, picasso, userActionsListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GRAPH.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        speakers_list.layoutManager = GridLayoutManager(activity, 3)
        speakers_list.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        userActionsListener.subscribe()
    }

    override fun onResume() {
        super.onResume()

        activity.setTitle(R.string.title_speakers)

    }

    override fun onStop() {
        super.onStop()

        userActionsListener.unsubscribe()
    }

    override fun showSpeakers(speakers: List<Speaker>) {
        adapter.data = speakers
        adapter.notifyDataSetChanged()
    }

    override fun displaySpeakerDetails(speaker: Speaker) {
        startActivity(createSpeakerDetails(activity, speaker))
    }


}
