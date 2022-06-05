package com.risingstar.talentaachiva.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.References.EVENT
import com.risingstar.talentaachiva.domain.data.References.EVENT_PARTICIPANT
import com.risingstar.talentaachiva.domain.data.References.USER
import com.risingstar.talentaachiva.domain.data.References.USER_HISTORY

class DetailVM(val userId: String, val eventId: String) : ViewModel() {
    private val db = Firebase.firestore
    private val eventRef = db.collection(EVENT)
    private val userRef = db.collection(USER)

    private val _event = MutableLiveData<Event?>()
    fun events() : LiveData<Event?> {
        return _event
    }

    fun getDetail(){
        eventRef.document(eventId).get().addOnCompleteListener {
            if(it.isSuccessful){
                _event.value = it.result.toObject()
            }
        }
    }

    fun registerEvent(){
        eventRef.document(eventId).update(EVENT_PARTICIPANT, FieldValue.arrayUnion(userId))
        userRef.document(userId).update(USER_HISTORY,FieldValue.arrayUnion(eventId))
    }
}

class DetailFactory(
    private val userId:String,
    private val eventId:String,
): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailVM(userId,eventId) as T
        }
        throw IllegalArgumentException()
    }
}