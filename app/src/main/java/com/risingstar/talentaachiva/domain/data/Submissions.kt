package com.risingstar.talentaachiva.domain.data

import java.util.*

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
