package com.risingstar.talentaachiva.feature.landing.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentRegisterBinding
import com.risingstar.talentaachiva.feature.dashboard.DashboardActivity
import com.risingstar.talentaachiva.feature.landing.LandingVM
import com.risingstar.talentaachiva.feature.landing.MainActivity

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private lateinit var viewmodel : LandingVM
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(LandingVM::class.java)
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclientid))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        viewmodel.user().observe(viewLifecycleOwner){
            if (it != null) {
                if(it.interest==null){
                    findNavController().navigate(R.id.navigate_interest)
                }
                else{
                    val intent = Intent(requireActivity(), DashboardActivity::class.java)
                    intent.putExtra(DashboardActivity.CURRENT_USER_ID,it.userId)
                    startActivity(intent)
                }
            }
        }

        with(binding){
            clickHereLog.setOnClickListener {
                it.findNavController().navigate(R.id.register_to_login)
            }
            btnSignInGoogle.setOnClickListener{
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, MainActivity.RC_SIGN_IN)
            }
            registerButton.setOnClickListener {
                viewmodel.register(
                    emailEdit.text.toString(),
                    passwordEdit.text.toString(),
                    nameEdit.text.toString()
                )
            }
        }

        return binding.root
    }
}