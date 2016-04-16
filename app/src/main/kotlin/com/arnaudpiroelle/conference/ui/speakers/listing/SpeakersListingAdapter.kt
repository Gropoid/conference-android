package com.arnaudpiroelle.conference.ui.speakers.listing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.model.Speaker
import com.squareup.picasso.Picasso
import java.util.*

class SpeakersListingAdapter(val context: Context, val picasso: Picasso, val userActionsListener: SpeakersListingContract.UserActionsListener) : RecyclerView.Adapter<SpeakerViewHolder>() {

    var data: List<Speaker> = ArrayList()

    override fun onBindViewHolder(viewHolder: SpeakerViewHolder, position: Int) {
        viewHolder.bindView(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerViewHolder {
        return SpeakerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_speaker, parent, false), picasso, userActionsListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
