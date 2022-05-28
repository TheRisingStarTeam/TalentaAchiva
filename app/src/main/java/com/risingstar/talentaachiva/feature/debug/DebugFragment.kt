package com.risingstar.talentaachiva.feature.debug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentDebugBinding

class DebugFragment : Fragment() {
    private lateinit var binding : FragmentDebugBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDebugBinding.inflate(inflater, container, false)

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
        return binding.root
    }

}