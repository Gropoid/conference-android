package com.arnaudpiroelle.conference.ui.speakers.listing

import com.arnaudpiroelle.conference.core.database.dao.SpeakerDao
import com.arnaudpiroelle.conference.model.Speaker
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


class SpeakersListingPresenter(val view: SpeakersListingContract.View, val speakerDao: SpeakerDao) : SpeakersListingContract.UserActionsListener {

    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe() {
        subscription = speakerDao.getSpeakers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showSpeakers(it)
                }
    }

    override fun unsubscribe() {
        subscription.unsubscribe()
    }

    override fun openSpeakerDetails(speaker: Speaker) {
        view.displaySpeakerDetails(speaker)
    }

}