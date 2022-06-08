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

    init{
        _currentUser.value = mAuth.currentUser
    }

    fun register(email:String, password: String, name: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    initUser(task.result.user, name)
                }
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
}