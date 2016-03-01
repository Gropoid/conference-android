package com.arnaudpiroelle.conference.model

import java.util.Date

class Block {

    var id: String? = null
    var title: String? = null
    var subtitle: String? = null
    var type: String? = null
    var start: Date? = null
    var end: Date? = null

    enum class BlockType {
        FREE,
        BREAK
    }

}
