package com.arnaudpiroelle.conference.ui.speakers.details

import com.arnaudpiroelle.conference.R
import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


class SpeakerDetailsPresenter(val view: SpeakerDetailsContract.View, val speakerDao: SpeakerDao) : SpeakerDetailsContract.UserActionsListener {

    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe(speakerId: String) {
        subscription = speakerDao.getSpeaker(speakerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showSpeaker(it)
                    view.refreshMenu(it)
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

}