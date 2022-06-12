package com.risingstar.talentaachiva.domain.data

import android.location.Address
import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "identity")
data class Identity (
    var userId : String? = null,
    var profilePic:String? = null,
    var name : String? = null,
    var dateOfBirth : Date? = null,
    var phoneNumber : String? = null,
    var email : String? = null,
    var address : Address? = null,
    var interest: List<Interest>? = null,
    var socialMedia: List<String>? = null,
    var organizerId: String? = null,
    var history : List<String>? = null,
    var recommendations: List<String>? = null,
    var todo : List<Todo>? = null
) : Parcelable

@Parcelize
data class Interest(
    var name : String? =null,
    var picture : String? = null
) : Parcelable

@Parcelize
data class Todo (
    var assignmentId: String? = null,
    var eventId : String? = null,
    var submission : String? = null
) : Parcelable