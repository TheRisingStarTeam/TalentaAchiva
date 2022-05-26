package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "announcement")
data class Announcements (
    val announcementId : String? = null,
    val eventId : String? = null,
    val winner : List<String>? = null,
    val submission : List<String>? = null,
    val extraContent : String? = null
)
