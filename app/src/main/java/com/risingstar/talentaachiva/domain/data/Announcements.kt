package com.risingstar.talentaachiva.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Announcements (
    var announcementId : String? = null,
    var eventId : String? = null,
    var winner : List<String>? = null,
    var submission : List<String>? = null,
    var extraContent : String? = null
) : Parcelable
