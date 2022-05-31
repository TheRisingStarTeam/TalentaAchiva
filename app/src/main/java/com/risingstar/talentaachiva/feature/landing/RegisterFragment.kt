package com.risingstar.talentaachiva.feature.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private lateinit var viewmodel : LandingVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(LandingVM::class.java)
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)

        with(binding){
            clickHereLog.setOnClickListener {
                it.findNavController().navigate(R.id.register_to_login)
            }
            registerButton.setOnClickListener {
                viewmodel.register(
                    emailEdit.text.toString(),
                    passwordEdit.text.toString()
                )
            }
        }

        return binding.root
    }
}