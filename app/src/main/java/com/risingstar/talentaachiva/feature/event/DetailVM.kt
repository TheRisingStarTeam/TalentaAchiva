package com.risingstar.talentaachiva.feature.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Event

class DetailVM(eventId:String) : ViewModel() {
    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")
    val currentUser = mAuth.currentUser

    private val _registerEventResult = MutableLiveData<Boolean?>()
    fun registerEventResult() : LiveData<Boolean?> {
        return _registerEventResult
    }

    private val _currentEvent = MutableLiveData<Event?>()
    fun currentEvent() : LiveData<Event?> {
        return _currentEvent
    }

    init{
        eventListener(eventId)
    }

    fun registerEvent(){
        eventRef.document(_currentEvent.value?.eventId.toString())
            .update(
                "participant",
                FieldValue.arrayUnion(currentUser?.uid)
            ).addOnCompleteListener {
                _registerEventResult.value = it.isSuccessful
            }
    }

    private fun eventListener(event:String){
        eventRef.document(event).addSnapshotListener { value, error ->
            _currentEvent.value = value?.toObject<Event>()
        }
    }

}

class DetailFactory(val data:String): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailVM(data) as T
        }
        throw IllegalArgumentException()
    }
}
