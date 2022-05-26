package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "assignment")
data class Assignments (
    val assignmentId : String? = null,
    val title : String? = null,
    val rules : String? = null,
    val description : String? = null,
    val eventId : String? = null
)
