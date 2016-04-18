package com.arnaudpiroelle.conference.ui.core.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.arnaudpiroelle.conference.BuildConfig
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.utils.Dates
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nav_header.view.*
import java.text.SimpleDateFormat


class NavigationHeaderView : FrameLayout {

    private val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy")

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        Picasso.with(context).load(R.drawable.logo).into(logo);

        name.text = BuildConfig.CONFERENCE_NAME
        date.text = dateFormat.format(Dates.fromUtc(BuildConfig.CONFERENCE_START))

        Picasso.with(context).load(BuildConfig.CONFERENCE_BACKGROUND).into(image_background)
    }
}