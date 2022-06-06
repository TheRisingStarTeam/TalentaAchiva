package com.risingstar.talentaachiva.feature.participant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.References.SUBMISSION
import com.risingstar.talentaachiva.domain.References.SUBMISSION_ID
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Submissions

class ParticipantVM(val assignmentId: String, val userId:String) : ViewModel() {
    private val db = Firebase.firestore
    private val assignmentRef = db.collection(ASSIGNMENT)
    private val submissionRef = db.collection(SUBMISSION)

    private val _assignment = MutableLiveData<Assignment?>()
    fun assignment() : LiveData<Assignment?> {
        return _assignment
    }

    init {
        getAssignment()
    }

    private fun getAssignment(){
        assignmentRef.document(assignmentId).get().addOnCompleteListener {
            if(it.isSuccessful){
                _assignment.value = it.result.toObject()
            }
        }
    }

    fun submitSubmission(submission:Submissions){
        submissionRef.add(submission).addOnCompleteListener {
            if(it.isSuccessful)
                submissionRef.document(it.result.id).update(SUBMISSION_ID,it.result.id)
        }
    }

}

class ParticipantFactory(
    private val assignmentId:String,
    private val userId: String
): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParticipantVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParticipantVM(assignmentId, userId) as T
        }
        throw IllegalArgumentException()
    }
}