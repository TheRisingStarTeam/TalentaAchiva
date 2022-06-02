package com.risingstar.talentaachiva.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val postId: String? = null,
    val title:String? = null,
    val content: String? = null,
    val author: String? = null
):
    Parcelable