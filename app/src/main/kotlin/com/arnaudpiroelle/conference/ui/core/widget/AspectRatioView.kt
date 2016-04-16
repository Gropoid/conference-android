package com.arnaudpiroelle.conference.ui.core.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.arnaudpiroelle.conference.R


class AspectRatioView : FrameLayout {

    private var mAspectRatio = 0f

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioView, defStyleAttr, 0)

        mAspectRatio = a.getFloat(R.styleable.AspectRatioView_aspectRatio, 0f)

        if (mAspectRatio == 0f) {
            throw IllegalArgumentException("You must specify an aspect ratio when using the AspectRatioView.")
        }

        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val width: Int
        val height: Int
        if (mAspectRatio != 0f) {
            width = widthSize
            height = (width / mAspectRatio).toInt()
            val exactWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
            val exactHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            super.onMeasure(exactWidthSpec, exactHeightSpec)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}