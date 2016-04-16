package com.arnaudpiroelle.conference.ui.explore.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.ui.explore.ExploreContract
import com.arnaudpiroelle.conference.ui.explore.model.TagWithSessions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_session.view.*
import kotlinx.android.synthetic.main.item_view_tag.view.*


class TagViewHolder(context: Context, parent: ViewGroup?, val picasso: Picasso, val userActionsListener: ExploreContract.UserActionsListener) : AbstractGroupViewHolder(context, R.layout.item_view_tag, parent) {

    private val maxSession: Int by lazy { context.resources.getInteger(R.integer.number_sessions_by_tag) }

    init {
        for (i in 1..maxSession) {
            LayoutInflater.from(itemView.context).inflate(R.layout.item_view_session, itemView.tag_contents, true)
        }
    }

    override fun bindView(data: Any?) {
        val tagWithSessions = data as TagWithSessions

        itemView.tag_title.text = tagWithSessions.tag.name

        itemView.tag_header.setOnClickListener {
            userActionsListener.openTagDetails(tagWithSessions.tag)
        }

        checkAndRemoveUselessSubView(tagWithSessions.sessions)
        tagWithSessions.sessions.take(maxSession).forEachIndexed { index, session ->

            val sessionView = itemView.tag_contents.getChildAt(index)
            sessionView.session_title.text = session.title
            sessionView.session_description.text = session.description
            sessionView.setOnClickListener {
                userActionsListener.openSessionDetails(session)
            }

            picasso.load(session.photoUrl).into(sessionView.session_thumbnail)
        }

    }

    private fun checkAndRemoveUselessSubView(sessions: List<Session>) {
        val viewContainer = itemView.tag_contents

        for (i in 0..(viewContainer.childCount - 1)) {
            if (i < sessions.size) {
                viewContainer.getChildAt(i).visibility = VISIBLE
            } else {
                viewContainer.getChildAt(i).visibility = GONE
            }
        }
    }

}