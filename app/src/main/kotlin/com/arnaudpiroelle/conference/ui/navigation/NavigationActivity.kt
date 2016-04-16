package com.arnaudpiroelle.conference.ui.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat.START
import android.view.MenuItem
import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.ui.core.BaseActivity
import com.arnaudpiroelle.conference.ui.explore.ExploreFragment
import com.arnaudpiroelle.conference.ui.speakers.listing.SpeakersListingFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : BaseActivity(), NavigationContract.View {

    val userActionsListener: NavigationContract.UserActionsListener by lazy { NavigationPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userActionsListener.restoreInstanceState(savedInstanceState)

        setContentView(R.layout.activity_navigation)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigation_view.setNavigationItemSelectedListener {
            userActionsListener.selectNavigationItem(it.itemId)
            true
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        if (savedInstanceState == null) {
            userActionsListener.selectNavigationItem(R.id.nav_explore)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        userActionsListener.saveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun checkNavigationItem(itemId: Int) {
        navigation_view.setCheckedItem(itemId);
    }

    override fun displayExplore() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, ExploreFragment())
                .commit()
    }

    override fun displaySpeakers() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, SpeakersListingFragment())
                .commit()
    }

    override fun displayScheduler() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, Fragment())
                .commit()
    }

    override fun displaySettings() {

    }

    override fun closeNavigation() {
        drawer_layout.closeDrawer(START)
    }


}