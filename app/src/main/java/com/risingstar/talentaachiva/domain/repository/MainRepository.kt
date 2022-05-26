package com.risingstar.talentaachiva.domain.repository

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignments
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity

data class CustomResult(
    var tag:String,
    var error:Boolean
)

class MainRepository(
    private val mAuth : FirebaseAuth,
    private val gAuth : GoogleSignInClient,
) {
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val assignRef = db.collection("assignments")

    val userIdentity = hashMapOf(
        "name" to "",
        "state" to "",
    )

    fun login(email:String, pass:String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, pass)
    }

    fun register(email:String, pass:String): Boolean {
        var result = false
        mAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
            val currentUser = mAuth.currentUser
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

    fun getUserData(): DocumentSnapshot? {
        var user : DocumentSnapshot? = null
        val currentUser = mAuth.currentUser
        if(currentUser!=null)
            userRef.document(currentUser.uid).get().addOnSuccessListener {
                user = it
            }
        return user
    }
    fun postEvent(event: Event){
        eventRef.add(event)
    }

    fun getEvents(): QuerySnapshot? {
        var eventList : QuerySnapshot? = null
        eventRef.get().addOnSuccessListener {
            eventList = it
        }
        return eventList
    }

    fun getEvents(query:List<String>): QuerySnapshot? {
        var eventList : QuerySnapshot? = null
        eventRef.whereArrayContainsAny("tags",query).get().addOnSuccessListener {
            eventList = it
        }
        return eventList
    }

    fun getEvents(type:String): QuerySnapshot? {
        var eventList : QuerySnapshot? = null
        val currentUser = mAuth.currentUser
        if(currentUser!=null)
            eventRef.whereEqualTo(type,currentUser.uid)
                .get().addOnSuccessListener {
                    eventList = it
                }
        return eventList
    }

    fun postIdentity(identity: Identity){
        val currentUser = mAuth.currentUser
        if(currentUser!=null)
            userRef.document(currentUser.uid).set(identity)
    }

    fun getAssignment(event: Event){
        val currentUser = mAuth.currentUser
        if(currentUser!=null)
            assignRef.whereEqualTo("eventId",event.eventId).get()
    }

    fun postAssignment(assignments: Assignments){
        val currentUser = mAuth.currentUser
        if(currentUser!=null)
            assignRef.add(assignments)
    }


}