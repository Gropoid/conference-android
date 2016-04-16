package com.arnaudpiroelle.conference.ui.sessions.listing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.model.Session
import com.squareup.picasso.Picasso
import java.util.*


class SessionListingAdapter(val context: Context, val picasso: Picasso, val userActionsListener: SessionListingContract.UserActionsListener) : RecyclerView.Adapter<SessionViewHolder>() {

    var datas : List<Session> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SessionViewHolder? {
        return SessionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_session, parent, false), picasso, userActionsListener)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bindView(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}