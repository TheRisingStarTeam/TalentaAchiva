package com.risingstar.talentaachiva.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.References.EVENT
import com.risingstar.talentaachiva.domain.References.EVENT_PARTICIPANT
import com.risingstar.talentaachiva.domain.References.EVENT_TAGS
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Event

class DashboardVM(val userID: String) : ViewModel(){
    private val db = Firebase.firestore
    private val eventRef = db.collection(EVENT)
    private val assignmentRef = db.collection(ASSIGNMENT)

    init{
        getEvents()
        //getAssignments()
        getRunningEvent()
    }

    private val _allEvents = MutableLiveData<List<Event>?>()
    fun allEvents() : LiveData<List<Event>?> {
        return _allEvents
    }

    private val _runEventId = MutableLiveData<List<String>?>()
    fun runEventId() : LiveData<List<String>?> {
        return _runEventId
    }

    private val _runningEvent = MutableLiveData<List<Event>?>()
    fun runningEvent() : LiveData<List<Event>?> {
        return _runningEvent
    }

    private val _allAssignments = MutableLiveData<List<Assignment>?>()
    fun allAssignments() : LiveData<List<Assignment>?> {
        return _allAssignments
    }

    fun getRunningEvent(){
        eventRef.whereArrayContains(EVENT_PARTICIPANT,userID).get()
            .addOnCompleteListener {task->
                val events = task.result.map{it.toObject<Event>()}
                val eventIds = mutableListOf<String>()
                _runningEvent.value = events
                events.forEach {event ->
                    event.eventId?.let { eventIds.add(it) }
                }
            }
    }

    private fun getEvents() {
        eventRef.get().addOnSuccessListener {result->
            _allEvents.value = result.map { it.toObject() }
        }
    }

    private val _searchEvent = MutableLiveData<List<Event>?>()
    fun searchEvent() : LiveData<List<Event>?> {
        return _searchEvent
    }

    fun getEventSearch(query:String) {
        eventRef.whereArrayContainsAny(EVENT_TAGS,stringToWords(query))
            .get().addOnSuccessListener {
                    result ->
                _searchEvent.value = result.map { it.toObject() }
            }

    }

    private fun getAssignments(){
//        var events : List<Event>
//        val assignments: MutableList<Assignment>? = null
//        eventRef.whereArrayContains(EVENT_PARTICIPANT,userID)
//            .get().addOnCompleteListener { task ->
//                events = task.result.map { it.toObject() }
//                events.forEach { event ->
//                    event.assignments?.forEach { assignment ->
//                        assignments?.add(assignment)
//                        _allAssignments.value = assignments
//                    }
//                }
//            }
        //assignmentRef.whereEqualTo(ASSIGNMENT_EVENT,)
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