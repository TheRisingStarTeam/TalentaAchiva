package com.risingstar.talentaachiva.domain.data

import android.location.Address
import androidx.room.Entity
import java.util.*

@Entity(tableName = "identity")
data class Identity (
    var userId : String? = null,
    var name : String? = null,
    var dateOfBirth : Date? = null,
    var phoneNumber : String? = null,
    var email : String? = null,
    var address : Address? = null,
    var interest: List<String>? = null,
    var socialMedia: List<String>? = null,
    var organizerId: String? = null,
    var history : List<String>? = null,
    var recommendations: List<String>? = null
)