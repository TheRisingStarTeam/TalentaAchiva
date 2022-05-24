package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity

@Entity(tableName = "assignment")
data class Assignments (
    val assignmentId : String,
    val title : String,
    val rules : String,
    val description : String,
    val eventId : String
)
