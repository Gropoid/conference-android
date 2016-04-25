package com.arnaudpiroelle.conference.ui.explore.viewholder

import android.content.Context
import android.text.TextUtils
import android.view.ViewGroup
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.utils.Dates
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.ui.explore.ExploreContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_keynote.view.*


class KeynoteViewHolder(val context: Context, parent: ViewGroup?, val userActionsListener: ExploreContract.UserActionsListener) : AbstractGroupViewHolder(context, R.layout.item_view_keynote, parent) {
    override fun bindView(data: Any?) {
        val keynote = data as Session
        itemView.title.text = keynote.title
        itemView.description.text = Dates.formatSessionPeriod(context, keynote.start!!, keynote.end!!)

        if (!TextUtils.isEmpty(keynote.photoUrl)) {
            Picasso.with(itemView.context)
                    .load(keynote.photoUrl)
                    .placeholder(R.drawable.placeholder_session)
                    .error(R.drawable.placeholder_session)
                    .into(itemView.image)
        } else {
            Picasso.with(itemView.context)
                    .load(R.drawable.placeholder_session)
                    .into(itemView.image)
        }

        itemView.keynote_container.setOnClickListener {
            userActionsListener.openSessionDetails(keynote)
        }
    }

}