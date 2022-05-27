package com.risingstar.talentaachiva.injections

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.risingstar.talentaachiva.domain.repository.MainRepository

object Injection {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("828023384580-cdsis9jro27i4ve34iib5qsu2fc6cgvi.apps.googleusercontent.com")
        .requestEmail()
        .build()
    fun provideRepository(): MainRepository {
//        val database = StoryDB.getDatabase(context)
//        val apiService = ApiConfig.getApiService()
//        val authDatastore = AuthDatastore.getInstance(context.dataStore)
        val mAuth = FirebaseAuth.getInstance()
        val db = Firebase.firestore
        return MainRepository(db,mAuth)
    }
}