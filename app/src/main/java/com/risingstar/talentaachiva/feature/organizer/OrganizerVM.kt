package com.risingstar.talentaachiva.feature.organizer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.EVENT
import com.risingstar.talentaachiva.domain.References.EVENT_ORGANIZER
import com.risingstar.talentaachiva.domain.data.Event

class OrganizerVM(val userID: String) : ViewModel(){
    private val db = Firebase.firestore
    private val eventRef = db.collection(EVENT)

    init{
        getOrganizedEvents()
    }

    private val _organizedEvents = MutableLiveData<List<Event>?>()
    fun organizedEvents() : LiveData<List<Event>?> {
        return _organizedEvents
    }

    private fun getOrganizedEvents() {
        eventRef.whereArrayContains(EVENT_ORGANIZER,userID).get()
            .addOnSuccessListener { result->
            _organizedEvents.value = result.map { it.toObject() }
        }
    }

    fun createEvent(event: Event){
        event.organizers = listOf(userID)
        eventRef.add(event)
    }



}

class OrganizerFactory(val data:String): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrganizerVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrganizerVM(data) as T
        }
        throw IllegalArgumentException()
    }
}