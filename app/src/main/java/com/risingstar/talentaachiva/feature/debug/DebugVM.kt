package com.risingstar.talentaachiva.feature.debug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity

class DebugVM : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")
    val currentUser = mAuth.currentUser


    private val _allEvents = MutableLiveData<List<Event>?>()
    fun allEvents() : LiveData<List<Event>?> {
        return _allEvents
    }

    private val _postEventResult = MutableLiveData<Boolean?>()
    fun postEventResult() : LiveData<Boolean?> {
        return _postEventResult
    }

    private val _postIdentityResult = MutableLiveData<Boolean?>()
    fun postIdentityResult() : LiveData<Boolean?> {
        return _postIdentityResult
    }

    private val _searchEvent = MutableLiveData<List<Event>?>()
    fun searchEvent() : LiveData<List<Event>?> {
        return _searchEvent
    }

    fun getEvents() {
        eventRef.get().addOnSuccessListener {result->
            _allEvents.value = result.map { it.toObject() }
        }

    }

//    fun getEventSearch(query:String) {
//        eventRef
////            .whereArrayContainsAny("hashtags",stringToWords(query))
//            .orderBy("hashtags")
//            .startAt(query)
//            .endAt(query+"\uf8ff")
//            .get().addOnSuccessListener {
//                    result ->
//                _searchEvent.value = result.map { it.toObject() }
//            }
//    }
    fun getEventSearch(query:String) {
        eventRef.whereArrayContainsAny("hashtags",stringToWords(query))
            .get().addOnSuccessListener {
                    result ->
                _searchEvent.value = result.map { it.toObject() }
            }
    }

    fun postEvents(event:Event){
        lateinit var searchTerm : MutableList<String>
        event.categories?.let { searchTerm.addAll(it) }
        event.name?.let { name -> stringToWords(name).let { searchTerm.addAll(it) } }
        event.hashtags = searchTerm
        eventRef.add(event).addOnCompleteListener { result->
            if (result.isSuccessful) {
                eventRef.document(result.result.id)
                    .update("eventId",result.result.id)
                    .addOnCompleteListener {
                        _postEventResult.value = it.isSuccessful
                }
            }
        }
    }

    fun postIdentity(identity : Identity){
        if(currentUser!=null) {
            identity.email = currentUser.email
            identity.userId = currentUser.uid
            userRef.document(currentUser.uid).set(identity, SetOptions.merge())
                .addOnCompleteListener {
                    _postIdentityResult.value = it.isSuccessful
                }
        }
    }

    private fun stringToWords(s : String) = s.trim().splitToSequence(' ')
        .filter { it.isNotEmpty() }
        .toList()

    fun logout() {
        mAuth.signOut()
    }
}

class DebugFactory: ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DebugVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DebugVM() as T
        }
        throw IllegalArgumentException()
    }
}