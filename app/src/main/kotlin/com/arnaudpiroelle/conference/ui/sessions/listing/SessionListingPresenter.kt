package com.arnaudpiroelle.conference.ui.sessions.listing

import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.TagDao
import com.arnaudpiroelle.conference.model.Session
import rx.Observable.combineLatest
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions


class SessionListingPresenter(val view: SessionListingContract.View, val tagDao: TagDao, val sessionDao: SessionDao) : SessionListingContract.UserActionsListener {
    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe(tagId: String) {
        subscription = combineLatest(tagDao.getTag(tagId), sessionDao.getSessionsByTag(tagId), { t1, t2 -> Pair(t1, t2) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showTag(it.first)
                    view.showSessions(it.second)
                }
    }

    override fun unsubscribe() {
        subscription.unsubscribe()
    }

    override fun openSessionDetails(session: Session) {
        view.displaySessionDetails(session)
    }

}