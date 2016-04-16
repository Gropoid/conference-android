package com.arnaudpiroelle.conference.ui.sessions.details

import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.ui.sessions.details.model.SessionData
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


class SessionDetailsPresenter(val view: SessionDetailsContract.View, val sessionDao: SessionDao, val speakerDao: SpeakerDao) : SessionDetailsContract.UserActionsListener {

    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe(sessionId: String) {
        subscription = Observable.combineLatest(sessionDao.getSession(sessionId), speakerDao.getSpeakers(), { session, speakers ->
            val sessionSpeakers = speakers.filter { speaker -> session.speakers!!.contains(speaker.id) }

            SessionData(session, sessionSpeakers)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showSession(it.session)
                    view.showSpeakers(it.speakers)
                }
    }

    override fun unsubscribe() {
        subscription.unsubscribe()
    }

}