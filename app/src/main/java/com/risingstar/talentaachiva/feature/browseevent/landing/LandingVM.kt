package com.risingstar.talentaachiva.feature.browseevent.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.risingstar.talentaachiva.domain.repository.MainRepository
import com.risingstar.talentaachiva.injections.Injection

class LandingVM(val repository: MainRepository): ViewModel() {

    //Maybe not yet, awight?
    //Awight...
    val currentUser = repository.currentUser

    fun login(email:String, pass:String){
        repository.login(email,pass)
    }

    fun register(email:String, pass:String){
        repository.register(email,pass)
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        repository.googleLogin(credential = credential)
    }
}

class LandingFactory : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LandingVM::class.java))
            return LandingVM(Injection.provideRepository()) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}