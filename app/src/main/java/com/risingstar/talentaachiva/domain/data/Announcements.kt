package com.risingstar.talentaachiva.domain.data

data class Announcements (
    val announcementId : String,
    val eventId : String,
    val winner : List<String>,
    val submission : List<String>,
    val extraContent : String
)
