package com.risingstar.talentaachiva.feature.debug

import android.content.Intent
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
import com.risingstar.talentaachiva.databinding.FragmentDebugBinding
import com.risingstar.talentaachiva.feature.browseevent.landing.MainActivity

class DebugFragment : Fragment() {
    private lateinit var viewmodel : DebugVM
    private lateinit var binding : FragmentDebugBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDebugBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DebugVM::class.java]

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclientid))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.btnAllEv.setOnClickListener{
            it.findNavController().navigate(R.id.debug_to_dashboard)
        }
        binding.btnSearch.setOnClickListener {
            it.findNavController().navigate(R.id.debug_to_search)
        }

        binding.btnProfile.setOnClickListener {
            it.findNavController().navigate(R.id.debug_to_profile)
        }

        binding.btnCreateEvent.setOnClickListener {
            it.findNavController().navigate(R.id.debug_to_eventcreate)
        }

        binding.btnLogout.setOnClickListener {
            viewmodel.logout()
            googleSignInClient.signOut()
            val intent = Intent(this.context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        return binding.root
    }

}