package com.risingstar.talentaachiva.domain.data

import androidx.room.Entity
import java.util.*

@Entity(tableName = "submission")
data class Submissions(
    var submissionId : String? = null,
    var title : String? = null,
    var dateOfSubmission : Date? = null,
    var type: String? = null,
    var content : String? = null,
    var description : String? = null,
    var author : List<String>? = null,
    var review : String? = null,
    var score: Int? = null,
)
