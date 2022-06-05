package com.risingstar.talentaachiva.feature.judge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class JudgeVM(userId: String, eventId: String, assignmentId: String) : ViewModel() {



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