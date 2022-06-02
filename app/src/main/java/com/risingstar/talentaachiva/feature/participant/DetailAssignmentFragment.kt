package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeDetailAssignmentBinding

class DetailAssignmentFragment : Fragment() {
    private lateinit var viewmodel : ParticipantVM
    private lateinit var binding : FragmentFakeDetailAssignmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeDetailAssignmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[ParticipantVM::class.java]

        return binding.root
    }


}