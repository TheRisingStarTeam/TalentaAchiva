package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity
import java.util.*

@Entity(tableName = "submission")
data class Submissions(
    val submissionId : String? = null,
    val title : String? = null,
    val dateOfSubmission : Date? = null,
    val type: String? = null,
    val content : String? = null,
    val description : String? = null,
    val author : List<String>? = null,
    val review : String? = null,
    val score: Int? = null,
)
