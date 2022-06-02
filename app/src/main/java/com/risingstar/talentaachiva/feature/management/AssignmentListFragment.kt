package com.risingstar.talentaachiva.feature.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeListassignmentBinding


class AssignmentListFragment : Fragment() {
    private lateinit var binding : FragmentFakeListassignmentBinding
    private lateinit var viewmodel : ManagementVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        binding = FragmentFakeListassignmentBinding
            .inflate(layoutInflater,container,false)




        return binding.root
    }
}