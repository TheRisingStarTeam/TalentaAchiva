package com.risingstar.talentaachiva.feature.browseevent.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    private lateinit var binding : FragmentLandingBinding
    private lateinit var viewmodel : LandingVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(LandingVM::class.java)
        binding = FragmentLandingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}