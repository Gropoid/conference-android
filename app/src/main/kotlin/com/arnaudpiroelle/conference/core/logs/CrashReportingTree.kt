package com.arnaudpiroelle.conference.core.logs

import timber.log.Timber


class CrashReportingTree : Timber.Tree() {
    override fun log(p0: Int, p1: String?, p2: String?, p3: Throwable?) {

    }
}