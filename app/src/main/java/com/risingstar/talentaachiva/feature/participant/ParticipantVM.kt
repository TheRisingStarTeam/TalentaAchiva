package com.risingstar.talentaachiva.feature.participant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.feature.event.DetailVM

class ParticipantVM(val event: String, val assignment: String) : ViewModel(){
    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")
    val currentUser = mAuth.currentUser

    private val _assignment = MutableLiveData<Assignment?>()
    fun assignment() : LiveData<Assignment?> {
        return _assignment
    }

    fun getAssignment(){
        eventRef.document(event)
            .collection("assignments")
            .document(assignment)
            .get().addOnCompleteListener {
                if(it.isSuccessful)
                    _assignment.value = it.result.toObject()
            }
    }

}

class AssignmentFactory(
    val event: String,
    val assignment: String
    ): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParticipantVM(event,assignment) as T
        }
        throw IllegalArgumentException()
    }
}