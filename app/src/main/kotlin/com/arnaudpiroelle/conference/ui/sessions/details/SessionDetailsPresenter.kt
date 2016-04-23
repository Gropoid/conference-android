package com.arnaudpiroelle.conference.ui.sessions.details

import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.core.database.dao.TagDao
import com.arnaudpiroelle.conference.ui.sessions.details.model.SessionData
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


class SessionDetailsPresenter(val view: SessionDetailsContract.View, val sessionDao: SessionDao, val speakerDao: SpeakerDao, val tagDao: TagDao) : SessionDetailsContract.UserActionsListener {

    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe(sessionId: String) {
        subscription = Observable.combineLatest(sessionDao.getSession(sessionId), speakerDao.getSpeakersBySessionId(sessionId), tagDao.getTagsBySessionId(sessionId), { session, speakers, tags ->
            val sessionSpeakers = speakers
            val sessionTags = tags

            SessionData(session, sessionSpeakers, sessionTags)
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showSession(it.session)

                    view.cleanTags()
                    it.tags.forEach {
                        view.addTag(it)
                    }

                    view.cleanSpeakers()
                    it.speakers.forEach {
                        view.addSpeaker(it)
                    }
                }
    }

    override fun unsubscribe() {
        subscription.unsubscribe()
    }

}