package com.arnaudpiroelle.conference

import android.app.Application
import android.content.Intent
import com.arnaudpiroelle.conference.core.inject.ConferenceComponent
import com.arnaudpiroelle.conference.core.inject.DaggerConferenceComponent
import com.arnaudpiroelle.conference.core.inject.module.ApplicationModule
import com.arnaudpiroelle.conference.core.logs.CrashReportingTree
import com.arnaudpiroelle.conference.services.SyncService
import com.facebook.stetho.Stetho
import timber.log.Timber
import timber.log.Timber.DebugTree

class ConferenceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        GRAPH = DaggerConferenceComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(DebugTree());
        } else {
            Timber.plant(CrashReportingTree());
        }

        // TODO: Change to alert manager
        startService(Intent(this, SyncService::class.java))
    }

    companion object {
        lateinit var GRAPH: ConferenceComponent
    }
}
