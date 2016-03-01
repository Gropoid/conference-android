package com.arnaudpiroelle.conference.ui.explorer

import android.os.Bundle
import android.support.v4.view.GravityCompat.START
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.arnaudpiroelle.conference.R
import kotlinx.android.synthetic.main.activity_explorer.*

class ExplorerActivity : AppCompatActivity(), ExplorerContract.View {

    val userActionsListener: ExplorerContract.UserActionsListener by lazy { ExplorerPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_explorer)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigation_view.setNavigationItemSelectedListener {
            userActionsListener.selectNavigationItem(it.itemId)
            true
        }

        if (savedInstanceState == null) {
            userActionsListener.selectNavigationItem(R.id.nav_sessions)
        }
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

    override fun displaySessions() {

    }

    override fun displaySpeakers() {

    }

    override fun displayScheduler() {

    }

    override fun displaySettings() {

    }

    override fun closeNavigation() {
        drawer_layout.closeDrawer(START)
    }


}