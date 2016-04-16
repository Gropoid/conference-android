package com.arnaudpiroelle.conference.ui.explore

import com.arnaudpiroelle.conference.core.database.dao.SessionDao
import com.arnaudpiroelle.conference.core.database.dao.TagDao
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.ui.explore.model.Group
import com.arnaudpiroelle.conference.ui.explore.model.Group.GroupType.KEYNOTE
import com.arnaudpiroelle.conference.ui.explore.model.Group.GroupType.TAG
import com.arnaudpiroelle.conference.ui.explore.model.TagWithSessions
import rx.Observable
import rx.Observable.combineLatest
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions
import java.util.*


class ExplorePresenter(val view: ExploreContract.View, val sessionDao: SessionDao, val tagDao: TagDao) : ExploreContract.UserActionsListener {

    private var subscription: Subscription = Subscriptions.empty()

    override fun subscribe() {

        subscription = combineLatest(loadKeynote(), loadTags(), loadSessions(), { keynote, tags, sessions ->
            val list = ArrayList<Group<*>>()
            list.addAll(keynote.map { Group(it, KEYNOTE) })
            list.addAll(tags.map { tag -> Group(TagWithSessions(tag, sessions.filter { session -> session.tags!!.contains(tag.id) }), TAG) }.filter { !it.data.sessions.isEmpty() })

            list
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showGroups(it)
                }
    }

    override fun unsubscribe() {
        subscription.unsubscribe()
    }

    private fun loadKeynote(): Observable<List<Session>> {
        return sessionDao.getKeynotes()
    }

    private fun loadSessions(): Observable<List<Session>> {
        return sessionDao.getSessions()
    }

    private fun loadTags(): Observable<List<Tag>> {
        return tagDao.getTags()
    }

    override fun openSessionDetails(session: Session) {
        view.displaySessionDetails(session)
    }

    override fun openTagDetails(tag: Tag) {
        view.displaySessions(tag)
    }

}