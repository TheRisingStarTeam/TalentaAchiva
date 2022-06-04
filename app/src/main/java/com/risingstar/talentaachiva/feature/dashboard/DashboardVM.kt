package com.risingstar.talentaachiva.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Event

class DashboardVM(val userID: String) : ViewModel(){
    private val db = Firebase.firestore
    private val eventRef = db.collection("events")

    private val _allEvents = MutableLiveData<List<Event>?>()
    fun allEvents() : LiveData<List<Event>?> {
        return _allEvents
    }

    private val _allAssignments = MutableLiveData<List<Assignment>?>()
    fun allAssignments() : LiveData<List<Assignment>?> {
        return _allAssignments
    }

    fun getEvents() {
        eventRef.get().addOnSuccessListener {result->
            _allEvents.value = result.map { it.toObject() }
        }
    }

    private val _searchEvent = MutableLiveData<List<Event>?>()
    fun searchEvent() : LiveData<List<Event>?> {
        return _searchEvent
    }

    fun getEventSearch(query:String) {
        eventRef.whereArrayContainsAny("hashtags",stringToWords(query))
            .get().addOnSuccessListener {
                    result ->
                _searchEvent.value = result.map { it.toObject() }
            }
    }

    fun getAssignments(){
        var events : List<Event>
        val assignments: MutableList<Assignment>? = null
        eventRef.whereArrayContains("participants",userID)
            .get().addOnCompleteListener { task ->
                events = task.result.map { it.toObject() }
                events.forEach { event ->
                    event.assignments?.forEach { assignment ->
                        assignments?.add(assignment)
                        _allAssignments.value = assignments
                    }
                }
            }
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

}

class DashboardFactory(val data:String): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardVM(data) as T
        }
        throw IllegalArgumentException()
    }
}