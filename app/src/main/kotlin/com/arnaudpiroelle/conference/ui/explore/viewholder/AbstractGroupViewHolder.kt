package com.arnaudpiroelle.conference.ui.explore.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class AbstractGroupViewHolder(context: Context, itemViewResId: Int, parent: ViewGroup?) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(itemViewResId, parent, false)) {
    abstract fun bindView(data: Any?);
}
