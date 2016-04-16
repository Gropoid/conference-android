package com.arnaudpiroelle.conference.ui.explore.model

import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Tag


data class TagWithSessions(val tag: Tag, val sessions: List<Session>)