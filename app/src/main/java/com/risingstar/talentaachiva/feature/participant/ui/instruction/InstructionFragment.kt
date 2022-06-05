package com.risingstar.talentaachiva.feature.participant.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentInstructionBinding
import com.risingstar.talentaachiva.feature.participant.ParticipantVM


class InstructionFragment : Fragment() {

    private lateinit var binding : FragmentInstructionBinding
    private lateinit var viewmodel : ParticipantVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ParticipantVM::class.java)
        binding = FragmentInstructionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}