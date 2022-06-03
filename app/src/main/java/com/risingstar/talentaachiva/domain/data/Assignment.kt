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
    var criteria: List<Criteria>? = null,
) : Parcelable

@Parcelize
data class Criteria(
    var name: String,
    var desc : String,
    var weight: Int
) : Parcelable

