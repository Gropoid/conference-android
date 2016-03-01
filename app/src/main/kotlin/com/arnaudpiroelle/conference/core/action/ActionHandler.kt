package com.arnaudpiroelle.conference.core.action

import android.content.Intent

interface ActionHandler {

    fun isSupportedAction(action: String): Boolean

    fun execute(intent: Intent)
}
