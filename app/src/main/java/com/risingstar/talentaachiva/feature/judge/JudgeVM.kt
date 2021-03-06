package com.risingstar.talentaachiva.feature.judge

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT_ID
import com.risingstar.talentaachiva.domain.References.SUBMISSION
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Submissions

class JudgeVM(val userId: String, val eventId: String, val assignmentId: String) : ViewModel() {
    private val db = Firebase.firestore
    private val assignmentRef = db.collection(ASSIGNMENT)
    private val submissionRef = db.collection(SUBMISSION)

    var chosenSubmissions : Submissions? = null

    init{
        getAssignment()
        getSubmission()
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
                Log.i("JudgeVM", "called getAssignment")
            }
        }
    }

    private fun getSubmission(){
        submissionRef.whereEqualTo(ASSIGNMENT_ID,assignmentId)
            .get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _submissions.value = task.result.map{it.toObject()}
                }
        }
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