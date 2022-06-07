package com.risingstar.talentaachiva.feature.management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References.ASSIGNMENT
import com.risingstar.talentaachiva.domain.References.EVENT
import com.risingstar.talentaachiva.domain.References.EVENT_ID
import com.risingstar.talentaachiva.domain.References.POST
import com.risingstar.talentaachiva.domain.References.USER
import com.risingstar.talentaachiva.domain.References.USER_ID
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity
import com.risingstar.talentaachiva.domain.data.Post

class ManagementVM(val userId: String, val eventId: String) : ViewModel() {

    private val db = Firebase.firestore
    private val thisEvent = db.collection(EVENT).document(eventId)
    private val postsRef = thisEvent.collection(POST)
    private val userRef = db.collection(USER)
    private val assignmentsRef = db.collection(ASSIGNMENT)

    lateinit var currentPost : Post
    lateinit var currentEvent: Event

    init{
        getAllPost()
        getAssignments()
        getParticipants()
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
    fun events() : LiveData<Event?>{
        return _event
    }

    private val _people = MutableLiveData<List<Identity?>>()
    fun people() : LiveData<List<Identity?>>{
        return _people
    }

    private fun getAllPost(){
        postsRef.get().addOnCompleteListener { result->
            if(result.isSuccessful)
                _posts.value = result.result.map { it.toObject() }
        }
    }

    private fun getAssignments(){
        var event: Event
        assignmentsRef.whereEqualTo(EVENT_ID,eventId).get().addOnCompleteListener {
            if(it.isSuccessful) {
                _assignments.value = it.result.toObjects()
            }
        }
    }

    private fun getParticipants(){
        val people = mutableListOf<String>()
        //_event.value?.participants?.let { people.addAll(it) }
        _event.value?.organizers?.let { people.addAll(it) }

        userRef.whereIn(USER_ID,people).get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                _people.value = task.result.map { it.toObject() }
            }
        }
    }

    fun createAssignment(assignment: Assignment){
        assignmentsRef.add(assignment)
    }

    fun createPost(post:Post){
        post.author = userId
        postsRef.add(post)
    }

}

class ManagementFactory(private val userId:String, private val eventId:String): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagementVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ManagementVM(userId,eventId) as T
        }
        throw IllegalArgumentException()
    }
}