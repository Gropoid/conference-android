package com.arnaudpiroelle.conference.ui.sessions.details.model

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Tag


data class SessionData(val session: Session, val speakers: List<Speaker>, val tags: List<Tag>)