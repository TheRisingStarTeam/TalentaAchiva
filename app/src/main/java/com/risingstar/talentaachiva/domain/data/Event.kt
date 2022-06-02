package com.risingstar.talentaachiva.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
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
    val status: String? = null,
    val posts: List<Post>? = null
) : Parcelable
