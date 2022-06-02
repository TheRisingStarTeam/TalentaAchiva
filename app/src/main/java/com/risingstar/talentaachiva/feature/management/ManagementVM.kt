package com.risingstar.talentaachiva.feature.management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.domain.data.Post
import com.risingstar.talentaachiva.domain.data.Submissions

class ManagementVM(val event: Event) : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val userRef = db.collection("userIdentities")
//    private val postRef = db.collection("posts")
    private val eventRef = db.collection("events")
    private val submissionRef = db.collection("submissions")
    private val currentUser = mAuth.currentUser

    private val _allAssignments = MutableLiveData<List<Assignment>?>()
    fun allAssignments() : LiveData<List<Assignment>?> {
        return _allAssignments
    }

    private val _allPosts = MutableLiveData<List<Post>?>()
    fun allPosts() : LiveData<List<Post>?> {
        return _allPosts
    }

    fun getAssignments(eventId:String){
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
                    _allAssignments.value = assignments
                }
    }

    fun getParticipantList(){
        userRef.whereArrayContainsAny("userId",event.participants!!)
            .get().addOnCompleteListener {

            }
    }

    fun getPosts(){
        eventRef.document(event.eventId.toString()).get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                    _allPosts.value = it.result.toObject<Event>()?.posts
                }
    }

    fun createPost(post: Post){
        if(currentUser!=null)
            eventRef.document()
    }

    fun postSubmission(submission: Submissions){
        if(currentUser!=null)
            submissionRef.add(submission)
    }

    fun getSubmission(){
        if(currentUser!=null)
        submissionRef.whereArrayContains("authorId",currentUser.uid)
            .get().addOnCompleteListener {

            }
    }
}

class ManagementFactory(val event:Event): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ManagementVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ManagementVM(event) as T
        }
        throw IllegalArgumentException()
    }
}

