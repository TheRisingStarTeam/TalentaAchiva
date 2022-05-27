package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "event")
data class Event(
    var eventId : String? = null,
    var banner : String? = null,
    var active : Boolean? = null,
    var name : String? = null,
    var categories : List<String>? = null,
    var hashtags : String? = null,
    var rules : String? = null,
    var description: String? = null,
    var participants : List<String>? = null,
    var organizers : List<String>? = null,
    var tos : String? = null,
    var assignments : List<Assignment>? = null
)
