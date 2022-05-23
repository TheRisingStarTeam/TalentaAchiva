package com.risingstar.talentaachiva.domain.data

import android.location.Address
import java.util.*

data class Identity (
    val userId : String,
    val name : String? = null,
    val dateOfBirth : Date? = null,
    val phoneNumber : String? = null,
    val email : String? = null,
    val address : Address? = null,
    val interest: List<String>? = null,
    val socialMedia: List<String>? = null,
    val organizerId: String? = null,
    val history : List<String>? = null,
    val recommendations: List<String>? = null
)