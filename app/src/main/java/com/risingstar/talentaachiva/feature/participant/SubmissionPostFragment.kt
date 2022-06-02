package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeCreateSubmissionBinding

class SubmissionPostFragment : Fragment() {

    private lateinit var viewmodel : ParticipantVM
    private lateinit var binding : FragmentFakeCreateSubmissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeCreateSubmissionBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[ParticipantVM::class.java]

        return binding.root
    }
}