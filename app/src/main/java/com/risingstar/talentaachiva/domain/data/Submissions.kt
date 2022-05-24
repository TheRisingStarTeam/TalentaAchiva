package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity
import java.util.*

@Entity(tableName = "submission")
data class Submissions(
    val submissionId : String,
    val title : String,
    val dateOfSubmission : Date,
    val type: String,
    val content : String,
    val description : String,
    val author : List<String>,
    val review : String,
    val score: Int,
)
