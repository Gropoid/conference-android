package com.arnaudpiroelle.conference.core.inject

import com.arnaudpiroelle.conference.core.inject.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ConferenceComponent {

}
