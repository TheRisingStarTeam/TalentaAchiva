package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "event")
data class Event(
    var eventId : String? = null,
    val banner : String? = null,
    val active : Boolean? = null,
    val name : String? = null,
    val categories : List<String>? = null,
    val hashtags : String? = null,
    val rules : String? = null,
    val description: String? = null,
    val participants : List<String>? = null,
    val organizers : List<String>? = null,
    val tos : String? = null,
    val postsId : String? = null,
    val assignments : String? = null
)
