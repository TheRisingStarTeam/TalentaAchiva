package com.risingstar.talentaachiva.feature.browseevent.landing

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.risingstar.talentaachiva.domain.repository.MainRepository

class LandingVM(val repository: MainRepository): ViewModel() {

    fun login(email:String, pass:String): Task<AuthResult> {
        return repository.login(email,pass)
    }

    fun register(email:String, pass:String){
        repository.register(email,pass)
    }
}