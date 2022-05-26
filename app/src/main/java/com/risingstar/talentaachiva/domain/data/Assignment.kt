package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "assignment")
data class Assignment (
    var assignmentId : String? = null,
    var title : String? = null,
    var rules : String? = null,
    var description : String? = null,
)
