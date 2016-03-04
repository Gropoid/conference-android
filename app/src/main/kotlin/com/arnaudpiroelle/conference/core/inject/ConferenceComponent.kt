package com.arnaudpiroelle.conference.core.inject

import com.arnaudpiroelle.conference.core.inject.module.ApiModule
import com.arnaudpiroelle.conference.core.inject.module.ApplicationModule
import com.arnaudpiroelle.conference.core.inject.module.DatabaseModule
import com.arnaudpiroelle.conference.services.SyncService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        DatabaseModule::class,
        ApiModule::class)
)
interface ConferenceComponent {
    fun inject(syncService: SyncService)

}
