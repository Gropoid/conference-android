package com.arnaudpiroelle.conference.ui.speakers.listing

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.arnaudpiroelle.conference.model.Speaker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_speaker.view.*

class SpeakerViewHolder(itemView: View, val userActionsListener: SpeakersListingContract.UserActionsListener) : RecyclerView.ViewHolder(itemView) {

    fun bindView(speaker: Speaker) {
        itemView.speaker_name.text = speaker.name

        if(!TextUtils.isEmpty(speaker.thumbnailUrl)){
            Picasso.with(itemView.context).load(speaker.thumbnailUrl)
                    .fit()
                    .centerCrop()
                    .into(itemView.speaker_image)
        }

        itemView.setOnClickListener {
            userActionsListener.openSpeakerDetails(speaker)
        }
    }

}
