package com.arnaudpiroelle.conference.ui.explorer

import android.support.annotation.IdRes


interface ExplorerContract {
    interface View {
        fun checkNavigationItem(@IdRes itemId: Int)
        fun displaySessions()
        fun displaySpeakers()
        fun displayScheduler()
        fun displaySettings()
        fun closeNavigation()
    }

    interface UserActionsListener {
        fun selectNavigationItem(itemId: Int)
    }
}