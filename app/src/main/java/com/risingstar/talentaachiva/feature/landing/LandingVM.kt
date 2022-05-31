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

class LandingVM : ViewModel() {

    private val mAuth = FirebaseAuth.getInstance()

    private val _currentUser = MutableLiveData<FirebaseUser>()
    fun currentUser() : LiveData<FirebaseUser> {
        return _currentUser
    }

    init{
        _currentUser.value = mAuth.currentUser
    }

    fun register(email:String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _currentUser.value = task.result.user
            }
    }

     fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.result != null)
                _currentUser.value = task.result.user
            }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
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
}