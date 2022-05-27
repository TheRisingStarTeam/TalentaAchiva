package com.risingstar.talentaachiva.domain.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Identity
import com.risingstar.talentaachiva.domain.data.Submissions

class MainRepository(
    db: FirebaseFirestore,
    val mAuth: FirebaseAuth
) {

    private val userRef = db.collection("userIdentities")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")

    val currentUser = mAuth.currentUser

    fun login(email:String, pass:String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, pass)
    }

    fun googleLogin(credential: AuthCredential): Boolean {
        var success = false
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GoogleFirebaseAuth", "signInWithCredential:success")
                    success = task.isSuccessful
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleFirebaseAuth", "signInWithCredential:failure", task.exception)
                    success = task.isSuccessful
                }
            }
        return success
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

    fun getEventSearch(query:List<String>): List<Event>? {
        var events : List<Event>? = null
        eventRef.whereArrayContainsAny("tags",query)
            .get().addOnSuccessListener {
                    result ->
                events = result.map { it.toObject() }
        }
        return events
    }

    fun getActiveEvents(role:String): List<Event> {
        val events = ArrayList<Event>()
        if(currentUser!=null)
            eventRef.whereEqualTo(role,currentUser.uid)
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

    fun getAssignments(eventId:String): List<Assignment> {
        val assignments = ArrayList<Assignment>()
        if(currentUser!=null)
            eventRef.document(eventId)
                .collection("assignments")
                .get().addOnSuccessListener { result ->
                    for(document in result){
                        val assignment = document.toObject(Assignment::class.java)
                        assignment.assignmentId = document.id
                        assignments.add(assignment)
                    }
                }
        return assignments
    }

    fun postAssignment(assignment: Assignment, eventId:String){
        if(currentUser!=null)
            eventRef.document(eventId)
                .collection("assignments")
                .add(assignment)
    }

    fun getAllSubmissionForParticipant(): ArrayList<Submissions> {
        val submissions = ArrayList<Submissions>()
        if(currentUser!=null)
            submissionRef.whereArrayContains("authorId",currentUser.uid)
                .get().addOnSuccessListener { result ->
                    for(document in result){
                        val submission = document.toObject(Submissions::class.java)
                        submission.submissionId = document.id
                        submissions.add(submission)
                    }
                }
        return submissions
    }

    fun getAllSubmissionsForOrganizer(assignmentId: String): ArrayList<Submissions> {
        val submissions = ArrayList<Submissions>()
        if(currentUser!=null)
            submissionRef.whereEqualTo("assignmentId",assignmentId)
                .get().addOnSuccessListener { result ->
                    for(document in result){
                        val submission = document.toObject(Submissions::class.java)
                        submission.submissionId = document.id
                        submissions.add(submission)
                    }
                }
        return submissions
    }

    fun postSubmission(submission: Submissions){
        if(currentUser!=null)
            submissionRef.add(submission)
    }

    fun updateSubmissionScore(score:Int,submissionId:String){
        submissionRef.document(submissionId).update("score",score)
    }

    fun addParticipant(participantId:String,eventId:String){
        eventRef.document(eventId)
            .update("participants", FieldValue.arrayUnion(participantId))
    }

    fun removeParticipant(participantId:String,eventId:String){
        eventRef.document(eventId)
            .update("participants", FieldValue.arrayRemove(participantId))
    }

    fun addOrganizer(organizerId:String, eventId:String){
        eventRef.document(eventId)
            .update("organizers", FieldValue.arrayUnion(organizerId))
    }

    fun removeOrganizer(organizerId:String, eventId:String){
        eventRef.document(eventId)
            .update("participants", FieldValue.arrayRemove(organizerId))
    }

}