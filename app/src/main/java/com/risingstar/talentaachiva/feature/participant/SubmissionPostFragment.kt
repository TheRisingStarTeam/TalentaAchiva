package com.risingstar.talentaachiva.feature.participant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeSubmissionPostBinding

class SubmissionPostFragment : Fragment() {

    private lateinit var viewmodel : AssignmentVM
    private lateinit var binding : FragmentFakeSubmissionPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeSubmissionPostBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[AssignmentVM::class.java]

        return binding.root
    }
}