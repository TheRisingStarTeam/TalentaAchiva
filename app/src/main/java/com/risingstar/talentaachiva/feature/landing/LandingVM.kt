package com.risingstar.talentaachiva.feature.landing

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.References
import com.risingstar.talentaachiva.domain.data.Identity

class LandingVM : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private val userRef = db.collection(References.USER)

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    fun currentUser() : LiveData<FirebaseUser?> {
        return _currentUser
    }

    private val _user = MutableLiveData<Identity?>()
    fun user() : LiveData<Identity?>{
        return _user
    }

    init{
        _currentUser.value = mAuth.currentUser
        checkUser()
    }

    fun register(email:String, password: String, name: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    initUser(task.result.user, name)
                }
            }
    }

    fun checkUser(){
        userRef.document(mAuth.uid.toString()).get().addOnCompleteListener {
            if(it.isSuccessful)
                _user.value = it.result.toObject()
        }
    }

    private fun initUser(user: FirebaseUser?, name: String) {
        val identity = Identity(
            userId = user?.uid,
            name,
            null,
            null,
            user?.email,
            null,
            null,
            null,
            null,
            null,
            null,
        )
        userRef.document(identity.userId.toString()).set(identity).addOnCompleteListener {
            _currentUser.value = user
            userRef.document(identity.userId.toString()).get().addOnCompleteListener {
                _user.value = it.result.toObject()
            }
        }

    }

    fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.result != null)
                    _currentUser.value = task.result.user

            }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _currentUser.value =  mAuth.currentUser
                } else {
                    _currentUser.value =  mAuth.currentUser
                }
            }
    }

    fun googleLogin(data: Intent?){
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
        }
    }

//    fun checkIfInterestExists(){

//    }
}