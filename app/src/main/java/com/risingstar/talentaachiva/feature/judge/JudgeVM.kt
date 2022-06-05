package com.risingstar.talentaachiva.feature.judge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Score
import com.risingstar.talentaachiva.domain.data.Submissions

class JudgeVM(val userId: String, val eventId: String, val assignmentId: String) : ViewModel() {
    private val db = Firebase.firestore
    private val assignmentRef = db.collection("assignments")
    private val submissionRef = db.collection("submissions")

    init{
        getAssignment()
    }

    private val _assignment = MutableLiveData<Assignment?>()
    fun assignment() : LiveData<Assignment?> {
        return _assignment
    }

    private val _submissions = MutableLiveData<List<Submissions?>>()
    fun submission() : LiveData<List<Submissions?>> {
        return _submissions
    }

    private fun getAssignment(){
        assignmentRef.document(assignmentId).get().addOnCompleteListener {
            if(it.isSuccessful){
                _assignment.value = it.result.toObject()
            }
        }
    }

    private fun getSubmission(){
        submissionRef.whereEqualTo("assignmentId",assignmentId)
            .get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _submissions.value = task.result.map{it.toObject()}
                }
        }
    }

    private fun gradeSubmission(submissionId : String, score:Score){
        submissionRef.document(submissionId).update("score",FieldValue.arrayUnion(score))
    }


}

class JudgeFactory(
    private val userId:String,
    private val eventId:String,
    private val assignmentId:String
    ): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JudgeVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JudgeVM(userId,eventId,assignmentId) as T
        }
        throw IllegalArgumentException()
    }
}