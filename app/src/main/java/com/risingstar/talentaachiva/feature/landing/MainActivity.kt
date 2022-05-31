package com.risingstar.talentaachiva.feature.landing

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.risingstar.talentaachiva.databinding.ActivityMainBinding
import com.risingstar.talentaachiva.feature.dashboard.MainDebugActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var email : String
    private lateinit var pass : String

    private lateinit var viewmodel : LandingVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()

        viewmodel = ViewModelProvider(this)[LandingVM::class.java]
        viewmodel.currentUser().observe(this) {
            if (it != null) {
                val intent = Intent(this, MainDebugActivity::class.java)
                startActivity(intent)
            }
        }

//        binding.lgnBtn.setOnClickListener {
//            email = binding.emailEt.text.toString()
//            pass = binding.passEt.text.toString()
//            viewmodel.login(email, pass)
//        }
//        binding.rgsBtn.setOnClickListener {
//            email = binding.emailEt.text.toString()
//            pass = binding.passEt.text.toString()
//            viewmodel.register(email, pass)
//        }
//        binding.btnLgt.setOnClickListener {
//            mAuth.signOut()
//            googleSignInClient.signOut()
//        }
//        binding.signGoogle.setOnClickListener {
//            val signInIntent = googleSignInClient.signInIntent
//            startActivityForResult(signInIntent, RC_SIGN_IN)
//        }

        setContentView(binding.root)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            viewmodel.googleLogin(data)
        }
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }
}