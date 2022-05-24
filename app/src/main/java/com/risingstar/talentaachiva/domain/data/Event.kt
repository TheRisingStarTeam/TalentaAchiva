package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "event")
data class Event(
    val eventId : String,
    val banner : String,
    val active : Boolean,
    val name : String,
    val categories : List<String>,
    val hashtags : String,
    val rules : String,
    val description: String,
    val participants : List<String>,
    val organizers : List<String>,
    val tos : String,
    val postsId : String,
    val assignments : String
)
