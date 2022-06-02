package com.risingstar.talentaachiva.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val postId: String,
    val title:String,
    val content: String,
    val author: String
):
    Parcelable