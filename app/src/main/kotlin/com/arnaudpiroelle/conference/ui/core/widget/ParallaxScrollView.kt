package com.arnaudpiroelle.conference.ui.core.widget

import android.content.Context
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import com.arnaudpiroelle.conference.R


class ParallaxScrollView : LinearLayout {

    var scrollViewListener : View.OnScrollChangeListener? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var headerContainer: View
    private lateinit var toolbarContainer: View
    private lateinit var contentContainer: View

    override fun onFinishInflate() {
        super.onFinishInflate()

        if (childCount != 3) {
            throw IllegalStateException("ParallaxScrollView must host three direct child")
        }

        val scrollView = ScrollView(context)
        scrollView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        scrollView.isVerticalScrollBarEnabled = false;
        scrollView.isHorizontalScrollBarEnabled = false;

        val frameLayout = FrameLayout(context)
        frameLayout.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        frameLayout.clipChildren = false


        headerContainer = getChildAt(0)
        toolbarContainer = getChildAt(1)
        contentContainer = getChildAt(2)

        removeView(headerContainer)
        removeView(toolbarContainer)
        removeView(contentContainer)

        frameLayout.addView(headerContainer)
        frameLayout.addView(contentContainer)
        frameLayout.addView(toolbarContainer)

        scrollView.addView(frameLayout)
        addView(scrollView)

        scrollView.setOnScrollChangeListener { scrollView, x, y, oldX, oldY ->
            onScrollChanged(scrollView, x, y, oldX, oldY)

            scrollViewListener?.onScrollChange(scrollView, x, y,  oldX, oldY)
        }

        val vto = scrollView.viewTreeObserver
        if (vto.isAlive) {
            vto.addOnGlobalLayoutListener {
                recomputeHeaderAndScrollingMetrics(scrollView)
            }
        }

    }

    private fun recomputeHeaderAndScrollingMetrics(scrollView: ScrollView) {
        val toolbarHeightPixels = toolbarContainer.height

        val headerHeight = headerContainer.height

        val layoutParams = contentContainer.layoutParams as ViewGroup.MarginLayoutParams
        if (layoutParams.topMargin != headerHeight + toolbarHeightPixels) {
            layoutParams.topMargin = headerHeight + toolbarHeightPixels
            contentContainer.layoutParams = layoutParams
        }

        onScrollChanged(scrollView, 0, 0, 0, 0)
    }

    fun onScrollChanged(view: View, x: Int, y: Int, oldx: Int, oldy: Int) {
        val scrollY = view.scrollY

        val newTop = Math.max(headerContainer.height, scrollY).toFloat()
        toolbarContainer.translationY = newTop

        headerContainer.translationY = scrollY * 0.6f

        var gapFillProgress = 1f
        if (headerContainer.height != 0) {
            gapFillProgress = Math.min(
                    Math.max(
                            getProgress(scrollY, 0, headerContainer.height)
                            , 0f)
                    , 1f)
        }

        val mMaxHeaderElevation = resources.getDimensionPixelSize(R.dimen.header_elevation);
        ViewCompat.setElevation(toolbarContainer, gapFillProgress * mMaxHeaderElevation)
    }

    fun getProgress(value: Int, min: Int, max: Int): Float {
        if (min == max) {
            throw IllegalArgumentException("Max ($max) cannot equal min ($min)")
        }

        return (value - min) / (max - min).toFloat()
    }

}