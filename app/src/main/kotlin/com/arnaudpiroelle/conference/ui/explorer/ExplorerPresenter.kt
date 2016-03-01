package com.arnaudpiroelle.conference.ui.explorer

import android.support.annotation.IdRes
import com.arnaudpiroelle.conference.R


class ExplorerPresenter(val view: ExplorerContract.View) : ExplorerContract.UserActionsListener {

    @IdRes var currentItemId = -1

    override fun selectNavigationItem(itemId: Int) {
        if (itemId == currentItemId) {
            return
        }

        when (itemId) {
            R.id.nav_scheduler -> view.displayScheduler()
            R.id.nav_sessions -> view.displaySessions()
            R.id.nav_speakers -> view.displaySpeakers()
            R.id.nav_settings -> view.displaySettings()
        }

        currentItemId = itemId
        view.checkNavigationItem(currentItemId)

        view.closeNavigation()
    }

}