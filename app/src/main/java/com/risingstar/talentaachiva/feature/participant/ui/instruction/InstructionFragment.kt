package com.risingstar.talentaachiva.feature.participant.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentInstructionBinding


class InstructionFragment : Fragment() {

    private lateinit var binding : FragmentInstructionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentInstructionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}