package com.risingstar.talentaachiva.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignments
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity

data class CustomResult(
    var tag:String,
    var error:Boolean
)

class MainRepository {
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val mAuth = FirebaseAuth.getInstance()

    private val currentUser = mAuth.currentUser

    fun login(email:String, pass:String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, pass)
    }

    fun register(email:String, pass:String): Boolean {
        var result = false
        mAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
            if(currentUser!=null){
                val data = hashMapOf(
                    "email" to currentUser.email
                )
                userRef.document(currentUser.uid).set(data).addOnCompleteListener {
                    result = it.isSuccessful
                }
            }
        }
        return result
    }

    fun postEvent(event: Event): Boolean {
        var result = false
        eventRef.add(event).addOnCompleteListener {
            result = it.isSuccessful
        }
        return result
    }

    fun getEvents():  List<Event>? {
        var eventList :  List<Event>? = null
        eventRef.get().addOnSuccessListener {result->
            eventList = result.map { it.toObject() }
        }
        return eventList
    }

    fun getEvents(query:List<String>): List<Event>? {
        var events : List<Event>? = null
        eventRef.whereArrayContainsAny("tags",query)
            .get().addOnSuccessListener {
                    result ->
                events = result.map { it.toObject() }
        }
        return events
    }

    fun getEvents(type:String): List<Event> {
        val events = ArrayList<Event>()
        if(currentUser!=null)
            eventRef.whereEqualTo(type,currentUser.uid)
                .get().addOnSuccessListener { result ->
                    for(document in result){
                        val event = document.toObject(Event::class.java)
                        event.eventId = document.id
                        events.add(event)
                    }
                }
        return events
    }

    fun getIdentity(): Identity? {
        var identity : Identity? = null
        if(currentUser!=null)
            userRef.document(currentUser.uid)
                .get().addOnSuccessListener { result ->
                identity = result.toObject()
                    identity?.userId = result.id
            }
        return identity
    }

    fun postIdentity(identity: Identity){
        if(currentUser!=null)
            userRef.document(currentUser.uid).set(identity)
    }

    fun postAssignment(assignments: Assignments){
        if(currentUser!=null)
            eventRef.document()
    }

    fun getSubmission(){

    }


}