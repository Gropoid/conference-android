package com.arnaudpiroelle.conference.ui.explore

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.TagDao
import com.arnaudpiroelle.conference.core.utils.Intents.createSessionDetails
import com.arnaudpiroelle.conference.core.utils.Intents.createSessionListing
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.ui.core.BaseFragment
import com.arnaudpiroelle.conference.ui.explore.model.Group
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_explore.*
import javax.inject.Inject


class ExploreFragment : BaseFragment(), ExploreContract.View {

    @Inject lateinit var picasso: Picasso
    @Inject lateinit var sessionDao: SessionDao
    @Inject lateinit var tagDao: TagDao

    val userActionsListener: ExploreContract.UserActionsListener by lazy { ExplorePresenter(this, sessionDao, tagDao) }
    val adapter by lazy { ExploreAdapter(activity, picasso, userActionsListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GRAPH.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        explore_list.layoutManager = LinearLayoutManager(context)
        explore_list.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        userActionsListener.subscribe()
    }

    override fun onResume() {
        super.onResume()

        activity.setTitle(R.string.title_explore)
    }

    override fun onStop() {
        super.onStop()

        userActionsListener.unsubscribe()
    }

    override fun showGroups(it: List<Group<*>>) {
        adapter.datas = it

        adapter.notifyDataSetChanged()
    }

    override fun displaySessions(tag: Tag) {
        startActivity(createSessionListing(activity, tag))
    }

    override fun displaySessionDetails(session: Session) {
        startActivity(createSessionDetails(activity, session))
    }


}