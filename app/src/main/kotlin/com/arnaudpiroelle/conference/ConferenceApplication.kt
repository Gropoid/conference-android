package com.arnaudpiroelle.conference

import android.app.Application
import com.arnaudpiroelle.conference.core.inject.ConferenceComponent

class ConferenceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var GRAPH: ConferenceComponent
    }
}
