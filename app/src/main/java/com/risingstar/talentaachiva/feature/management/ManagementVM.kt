package com.risingstar.talentaachiva.feature.management

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT_EVENT
import com.risingstar.talentaachiva.domain.References.EVENT
import com.risingstar.talentaachiva.domain.References.POST
import com.risingstar.talentaachiva.domain.References.USER
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity
import com.risingstar.talentaachiva.domain.data.Post

class ManagementVM(val userId: String, val eventId: String) : ViewModel() {



    lateinit var currentPost : Post
    lateinit var currentEvent: Event
    lateinit var currentUser : Identity

    private val db = Firebase.firestore
    private val eventRef = db.collection(EVENT)
    private val thisEvent = eventRef.document(eventId)
    private val postsRef = thisEvent.collection(POST)
    private val userRef = db.collection(USER)
    private val assignmentRef = db.collection(ASSIGNMENT)

    init{
        getEvent()
        getAllPost()
        getCurrentUser()
        addPostListener()
        addAssignmentListener()
        //addEventListener()
        //getParticipants()
    }




    private val _posts = MutableLiveData<List<Post>?>()
    fun posts() : LiveData<List<Post>?> {
        return _posts
    }

    private val _assignments = MutableLiveData<List<Assignment>?>()
    fun assignments() : LiveData<List<Assignment>?>{
        return _assignments
    }

    private val _event = MutableLiveData<Event?>()
    fun event() : LiveData<Event?>{
        return _event
    }

    private val _people = MutableLiveData<List<Identity?>>()
    fun people() : LiveData<List<Identity?>>{
        return _people
    }

    private val _currentUser = MutableLiveData<Identity?>()
    fun currentUser() : LiveData<Identity?>{
        return _currentUser
    }

    private fun getCurrentUser() {
        userRef.document(userId).get().addOnCompleteListener {
            if(it.isSuccessful) {
                _currentUser.value = it.result.toObject()
                currentUser = it.result.toObject()!!
            }
        }
    }

    private fun getEvent() {
        thisEvent.get().addOnCompleteListener {
            if(it.isSuccessful) {
                val event = it.result.toObject<Event>()
                _event.value = event
                if (event != null) {
                    currentEvent = event
                }
//                if (event != null) {
//                    _assignments.value = event.assignments
//                    currentEvent = event
//                }

            }
        }
    }

    private fun getAllPost(){
        postsRef.get().addOnCompleteListener { result->
            if(result.isSuccessful)
                _posts.value = result.result.map { it.toObject() }
        }
    }

    private fun getPeople(){

    }



    private fun addPostListener() {
        postsRef.addSnapshotListener{value,e->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (value != null) {
                _posts.value = value.toObjects()
            }

        }
    }

//    private fun addEventListener() {
//        thisEvent.addSnapshotListener{value,e->
//            if (e != null) {
//                Log.w(TAG, "Listen failed.", e)
//                return@addSnapshotListener
//            }
//            if (value != null) {
//                val event = value.toObject<Event>()
//                if(event!=null){
//                    currentEvent = value.toObject()!!
//                    _event.value = currentEvent
//                    _assignments.value = currentEvent.assignments
//                }
//
//            }
//        }
//    }

        private fun addAssignmentListener() {
            assignmentRef.whereEqualTo(ASSIGNMENT_EVENT,eventId).addSnapshotListener{ value, e->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                if (value != null) {
                    _assignments.value = value.toObjects()
                }

            }
        }



    fun createAssignment(assignment: Assignment){
        assignment.eventId=eventId
        assignmentRef.add(assignment)
    }

    fun createPost(post:Post){
        post.author = userId
        postsRef.add(post)
    }

}

class ManagementFactory(
    private val userId:String,
    private val eventId:String
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagementVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ManagementVM(userId,eventId) as T
        }
        throw IllegalArgumentException()
    }
}