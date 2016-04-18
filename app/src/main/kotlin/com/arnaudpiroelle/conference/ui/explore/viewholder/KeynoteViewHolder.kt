package com.arnaudpiroelle.conference.ui.explore.viewholder

import android.content.Context
import android.view.ViewGroup
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.utils.TimeUtils
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.ui.explore.ExploreContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_keynote.view.*
import java.util.*


class KeynoteViewHolder(val context: Context, parent: ViewGroup?, val userActionsListener: ExploreContract.UserActionsListener) : AbstractGroupViewHolder(context, R.layout.item_view_keynote, parent) {
    override fun bindView(data: Any?) {
        val keynote = data as Session
        itemView.title.text = keynote.title
        itemView.description.text = getPeriode(keynote)

        Picasso.with(itemView.context).load(keynote.photoUrl).into(itemView.image)

        itemView.keynote_container.setOnClickListener {
            userActionsListener.openSessionDetails(keynote)
        }
    }

    private fun getPeriode(session: Session): String {
        var startTime: Long
        var endTime: Long
        val currentTime: Long = System.currentTimeMillis()

        startTime = if (session!!.start != null) session.start!!.time else 0
        endTime = if (session!!.end != null) session.end!!.time else Long.MAX_VALUE

        val stringBuilder = StringBuilder()
        if (currentTime >= startTime && currentTime < endTime) {
            stringBuilder.append(context.getString(R.string.in_progress))
        } else {
            val shortDate = TimeUtils.formatShortDate(context, session!!.start!!)
            stringBuilder.append(shortDate)

            if (startTime > 0) {
                stringBuilder.append(" / ")
                stringBuilder.append(TimeUtils.formatShortTime(context, Date(startTime)))
            }
        }
        return stringBuilder.toString()
    }

}