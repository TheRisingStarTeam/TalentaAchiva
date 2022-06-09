package com.risingstar.talentaachiva.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assignment (
    var assignmentId : String? = null,
    var title : String? = null,
    var rules : String? = null,
    var description : String? = null,
    var type : String? = null,
    var eventId : String? = null,
    var criteria: List<Criteria>? = null,
) : Parcelable

@Parcelize
data class Criteria(
    var name: String? = null,
    var desc : String? = null,
    var weight: Int? = 0
) : Parcelable

