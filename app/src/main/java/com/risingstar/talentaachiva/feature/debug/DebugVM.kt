package com.risingstar.talentaachiva.feature.debug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Event

class DebugVM : ViewModel() {

    val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")


    private val _allEvents = MutableLiveData<List<Event>?>()
    fun allEvents() : LiveData<List<Event>?> {
        return _allEvents
    }

    private val _postEventResult = MutableLiveData<Boolean?>()
    fun postEventResult() : LiveData<Boolean?> {
        return _postEventResult
    }

    private val _searchEvent = MutableLiveData<List<Event>?>()
    fun searchEvent() : LiveData<List<Event>?> {
        return _searchEvent
    }

    init{
        getEvents()
    }

    private fun getEvents() {
        eventRef.get().addOnSuccessListener {result->
            _allEvents.value = result.map { it.toObject() }
        }

    }

    fun getEventSearch(query:String) {
        eventRef.whereArrayContainsAny("tags",stringToWords(query))
            .get().addOnSuccessListener {
                    result ->
                _searchEvent.value = result.map { it.toObject() }

            }
    }

    fun postEvents(event:Event){
        eventRef.add(event).addOnCompleteListener { result->
            if (result.isSuccessful)
                eventRef.document(result.result.id).update("eventId",result.result.id).addOnCompleteListener {
                    _postEventResult.value = it.isSuccessful
                }
        }
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()
}

class DebugFactory(): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DebugVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DebugVM() as T
        }
        throw IllegalArgumentException()
    }
}