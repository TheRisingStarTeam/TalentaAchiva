package com.risingstar.talentaachiva.feature.landing.ui.landing

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
import com.risingstar.talentaachiva.databinding.FragmentLandingBinding
import com.risingstar.talentaachiva.feature.landing.LandingVM
import com.risingstar.talentaachiva.feature.landing.MainActivity

class LandingFragment : Fragment() {
    private lateinit var binding : FragmentLandingBinding
    private lateinit var viewmodel : LandingVM
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(LandingVM::class.java)
        binding = FragmentLandingBinding.inflate(layoutInflater,container,false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclientid))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        with(binding){
            buttonLoginMain.setOnClickListener {
                it.findNavController().navigate(R.id.landing_to_login)
            }
            btnRegisterMain.setOnClickListener {
                it.findNavController().navigate(R.id.landing_to_register)
            }
            btnSignInGoogle.setOnClickListener{
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, MainActivity.RC_SIGN_IN)
            }
        }

        return binding.root
    }

}