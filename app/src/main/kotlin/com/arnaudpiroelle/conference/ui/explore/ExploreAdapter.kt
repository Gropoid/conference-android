package com.arnaudpiroelle.conference.ui.explore

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.arnaudpiroelle.conference.ui.explore.model.Group
import com.arnaudpiroelle.conference.ui.explore.model.Group.GroupType.*
import com.arnaudpiroelle.conference.ui.explore.viewholder.AbstractGroupViewHolder
import com.arnaudpiroelle.conference.ui.explore.viewholder.KeynoteViewHolder
import com.arnaudpiroelle.conference.ui.explore.viewholder.MessageViewHolder
import com.arnaudpiroelle.conference.ui.explore.viewholder.TagViewHolder
import com.squareup.picasso.Picasso
import java.util.*


class ExploreAdapter(val context: Context, val picasso: Picasso, val userActionsListener: ExploreContract.UserActionsListener) : RecyclerView.Adapter<AbstractGroupViewHolder>() {

    var datas: List<Group<*>> = ArrayList()

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AbstractGroupViewHolder? {
        when (viewType) {
            VIEW_TYPE_MESSAGE -> return MessageViewHolder(context, parent)
            VIEW_TYPE_KEYNOTE -> return KeynoteViewHolder(context, parent, picasso, userActionsListener)
            VIEW_TYPE_TAG -> return TagViewHolder(context, parent, picasso, userActionsListener)
        }

        return null
    }

    override fun onBindViewHolder(holder: AbstractGroupViewHolder, position: Int) {
        holder.bindView(datas[position].data)
    }

    override fun getItemViewType(position: Int): Int {
        when (datas[position].type) {
            MESSAGE -> return VIEW_TYPE_MESSAGE
            KEYNOTE -> return VIEW_TYPE_KEYNOTE
            TAG -> return VIEW_TYPE_TAG
            else -> return -1
        }
    }

    companion object {
        const val VIEW_TYPE_MESSAGE = 0;
        const val VIEW_TYPE_KEYNOTE = 1;
        const val VIEW_TYPE_TAG = 2;
    }

}