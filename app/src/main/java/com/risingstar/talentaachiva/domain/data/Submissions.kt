package com.risingstar.talentaachiva.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Submissions(
    var submissionId : String? = null,
    var assignmentId : String? = null,
    var authorId : List<String>? = null,
    var title : String? = null,
    var dateOfSubmission : Date? = null,
    var type: String? = null,
    var content : String? = null,
    var description : String? = null,
    var judgement: List<Judgement>? = null,
) : Parcelable

@Parcelize
data class Judgement(
    var judge: String? = null,
    var review: String? = null,
    var score: List<Score>? = null
):Parcelable

@Parcelize
data class Score(
    var criteria: Criteria? = null,
    var amount: Int? = null
) : Parcelable
