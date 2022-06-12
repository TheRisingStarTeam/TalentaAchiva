package com.risingstar.talentaachiva.feature.grading

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.References.SUBMISSION
import com.risingstar.talentaachiva.domain.References.SUBMISSION_JUDGEMENT
import com.risingstar.talentaachiva.domain.References.USER
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Judgement
import com.risingstar.talentaachiva.domain.data.Submissions

class GradingVM(
    val userId: String,
    private val submissionId: String,
    private val assignmentId: String
) : ViewModel(){
    private val db = Firebase.firestore
    private val submissionRef = db.collection(SUBMISSION)
    private val assignmentRef = db.collection(ASSIGNMENT)
    private val userRef = db.collection(USER)
    private lateinit var currentSubmission : Submissions

    init {
        getAssignment()
        getSubmission()
    }



    private val _submissions = MutableLiveData<Submissions?>()
    fun submission() : LiveData<Submissions?> {
        return _submissions
    }

    private val _assignment = MutableLiveData<Assignment?>()
    fun assignment() : LiveData<Assignment?>{
        return _assignment
    }

    private fun getSubmission() {
        submissionRef.document(submissionId).get().addOnCompleteListener {
            if(it.isSuccessful) {
                _submissions.value = it.result.toObject()
                currentSubmission = _submissions.value!!
            }
        }
    }

    fun gradeSubmission(judgement: Judgement){
        judgement.judge = userId
        submissionRef.document(submissionId).update(
                SUBMISSION_JUDGEMENT,
                FieldValue.arrayUnion(judgement)
            )
    }

    private fun getAssignment() {
        assignmentRef.document(assignmentId).get().addOnCompleteListener {
            _assignment.value = it.result.toObject()
            Log.wtf("YABE", _assignment.value?.criteria?.get(0)?.name)
        }
    }

}

class GradingFactory(
    private val userId:String,
    private val submissionId:String,
    private val assignmentId : String
    ): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradingVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GradingVM(userId,submissionId, assignmentId) as T
        }
        throw IllegalArgumentException()
    }
}