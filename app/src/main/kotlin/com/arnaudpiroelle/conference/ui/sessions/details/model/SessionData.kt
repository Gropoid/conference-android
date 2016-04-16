package com.arnaudpiroelle.conference.ui.sessions.details.model

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker


data class SessionData(val session: Session, val speakers: List<Speaker>)