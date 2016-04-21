package com.arnaudpiroelle.conference.ui.speakers.details

import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


class SpeakerDetailsPresenter(val view: SpeakerDetailsContract.View, val speakerDao: SpeakerDao, val sessionDao: SessionDao) : SpeakerDetailsContract.UserActionsListener {

    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe(speakerId: String) {
        subscription = Observable.combineLatest(speakerDao.getSpeaker(speakerId), sessionDao.getSessionsBySpeaker(speakerId), {
            speaker, sessions ->
            SpeakerData(speaker, sessions.filter { it.speakers!!.contains(speakerId) })
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showSpeaker(it.speaker)
                    view.refreshMenu(it.speaker)

                    view.cleanSessions()

                    it.sessions.forEach {
                        view.addSession(it)
                    }
                }
    }

    override fun unsubscribe() {
        subscription.unsubscribe()
    }

    override fun selectOptionItem(itemId: Int) {
        when (itemId) {
            R.id.menu_speaker_github -> view.displayGithub()
            R.id.menu_speaker_twitter -> view.displayTwitter()
            R.id.menu_speaker_website -> view.displayWebsite()
        }
    }

    data class SpeakerData(val speaker: Speaker, val sessions: List<Session>)

}