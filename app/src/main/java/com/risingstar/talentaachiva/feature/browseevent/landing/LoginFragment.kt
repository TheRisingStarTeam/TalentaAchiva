package com.risingstar.talentaachiva.feature.browseevent.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentLoginBinding
import com.risingstar.talentaachiva.feature.browseevent.landing.MainActivity.Companion.RC_SIGN_IN

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewmodel : LandingVM
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclientid))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        viewmodel = ViewModelProvider(requireActivity()).get(LandingVM::class.java)
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)

        with(binding){
            btnNewAcc.setOnClickListener{
                it.findNavController().navigate(R.id.login_to_register)
            }
            btnLogin.setOnClickListener {
                viewmodel.login(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
            btnLoginGoogle.setOnClickListener {
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }

        return binding.root
    }
}