package com.risingstar.talentaachiva.feature.browseevent.participant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Submissions

class ParticipantVM : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")
    val currentUser = mAuth.currentUser

    private val _allAssignments = MutableLiveData<List<Assignment>?>()
    fun allAssignments() : LiveData<List<Assignment>?> {
        return _allAssignments
    }

    fun getAssignments(eventId:String){
        val assignments = ArrayList<Assignment>()
        if(currentUser!=null)
            eventRef.document(eventId)
                .collection("assignments")
                .get().addOnSuccessListener { result ->
                    for(document in result){
                        val assignment = document.toObject(Assignment::class.java)
                        assignment.assignmentId = document.id
                        assignments.add(assignment)
                    }
                    _allAssignments.value = assignments
                }
    }

    fun postSubmission(submission: Submissions){
        if(currentUser!=null)
            submissionRef.add(submission)
    }
}