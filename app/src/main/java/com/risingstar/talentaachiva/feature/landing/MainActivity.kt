package com.risingstar.talentaachiva.feature.landing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewmodel : LandingVM

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        val view = binding.root
        setContentView(view)

        Toast.makeText(this,"Welcome to $this", Toast.LENGTH_SHORT).show()

        viewmodel = ViewModelProvider(this)[LandingVM::class.java]

        viewmodel.user().observe(this){
            if (it != null) {
                if(it.interest!=null){
                    Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity)
                }
            }
        }


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