package com.arnaudpiroelle.conference.ui.sessions.listing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arnaudpiroelle.conference.model.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_session.view.*


class SessionViewHolder(itemView: View, val context: Context, val userActionsListener: SessionListingContract.UserActionsListener) : RecyclerView.ViewHolder(itemView) {
    fun bindView(session: Session) {
        itemView.session_title.text = session.title
        itemView.session_description.text = session.description
        itemView.setOnClickListener {
            userActionsListener.openSessionDetails(session)
        }

        Picasso.with(context).load(session.photoUrl).into(itemView.session_thumbnail)
    }
}