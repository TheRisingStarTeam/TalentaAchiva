package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity
import java.util.*

@Entity(tableName = "event")
data class Event(
    var eventId : String? = null,
    var banner : String? = null,
    var active : Boolean? = null,
    var name : String? = null,
    var categories : List<String>? = null,
    var hashtags : List<String>? = null,
    var rules : String? = null,
    var description: String? = null,
    var participants : List<String>? = null,
    var organizers : List<String>? = null,
    var tos : String? = null,
    var date : Date? = null,
    val organization: String? = null,
    val status: String? = null
) {


}
