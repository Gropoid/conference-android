package com.arnaudpiroelle.conference.ui.navigation

import android.os.Bundle
import android.support.annotation.IdRes


interface NavigationContract {
    interface View {
        fun checkNavigationItem(@IdRes itemId: Int)
        fun displayExplore()
        fun displaySpeakers()
        fun displayScheduler()
        fun displaySettings()
        fun closeNavigation()
    }

    interface UserActionsListener {
        fun selectNavigationItem(itemId: Int)
        fun saveInstanceState(outState: Bundle?)
        fun restoreInstanceState(savedInstanceState: Bundle?)
    }
}