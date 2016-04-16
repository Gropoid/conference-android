package com.arnaudpiroelle.conference.ui.navigation

import android.os.Bundle
import android.support.annotation.IdRes
import com.arnaudpiroelle.conference.R


class NavigationPresenter(val view: NavigationContract.View) : NavigationContract.UserActionsListener {

    var currentItemId = -1

    override fun selectNavigationItem(@IdRes itemId: Int) {
        if (itemId == currentItemId) {
            view.closeNavigation()
            return
        }

        currentItemId = itemId
        view.checkNavigationItem(currentItemId)

        when (itemId) {
            R.id.nav_scheduler -> view.displayScheduler()
            R.id.nav_explore -> view.displayExplore()
            R.id.nav_speakers -> view.displaySpeakers()
            R.id.nav_settings -> view.displaySettings()
        }

        view.closeNavigation()
    }

    override fun saveInstanceState(outState: Bundle?) {
        outState?.putInt("currentItemId", currentItemId);
    }

    override fun restoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            currentItemId = savedInstanceState.getInt("currentItemId")
        }
    }

}