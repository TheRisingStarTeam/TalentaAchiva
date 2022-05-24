package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "announcement")
data class Announcements (
    val announcementId : String,
    val eventId : String,
    val winner : List<String>,
    val submission : List<String>,
    val extraContent : String
)
