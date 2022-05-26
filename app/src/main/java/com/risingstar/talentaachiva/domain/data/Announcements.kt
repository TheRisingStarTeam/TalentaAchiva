package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "announcement")
data class Announcements (
    var announcementId : String? = null,
    var eventId : String? = null,
    var winner : List<String>? = null,
    var submission : List<String>? = null,
    var extraContent : String? = null
)
