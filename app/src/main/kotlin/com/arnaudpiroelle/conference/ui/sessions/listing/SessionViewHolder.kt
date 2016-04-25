package com.arnaudpiroelle.conference.ui.sessions.listing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.utils.Dates
import com.arnaudpiroelle.conference.model.Session
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_session.view.*


class SessionViewHolder(itemView: View, val context: Context, val userActionsListener: SessionListingContract.UserActionsListener) : RecyclerView.ViewHolder(itemView) {
    fun bindView(session: Session) {
        itemView.session_title.text = session.title
        itemView.session_description.text = session.description
        itemView.session_dates.text = Dates.formatSessionPeriod(context, session.start!!, session.end!!)

        itemView.setOnClickListener {
            userActionsListener.openSessionDetails(session)
        }

        if (!TextUtils.isEmpty(session.photoUrl)) {
            Picasso.with(context).load(session.photoUrl).placeholder(R.drawable.placeholder_session).error(R.drawable.placeholder_session).into(itemView.session_thumbnail)
        } else {
            Picasso.with(context).load(R.drawable.placeholder_session).into(itemView.session_thumbnail)
        }
    }
}