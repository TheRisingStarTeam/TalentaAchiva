package com.risingstar.talentaachiva.feature.management.ui.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentAssignmentsBinding
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.feature.management.ManagementVM
import com.risingstar.talentaachiva.feature.util.AssignmentAdapter

class AssignmentsFragment : Fragment() {

    private lateinit var binding : FragmentAssignmentsBinding
    private lateinit var viewmodel : ManagementVM
    private lateinit var rvAssignments: RecyclerView
    private lateinit var rvAdapter: AssignmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)
        binding = FragmentAssignmentsBinding.inflate(inflater, container, false)

        rvAssignments = binding.rvAssignments

        viewmodel.assignments().observe(viewLifecycleOwner){
            rvAdapter = AssignmentAdapter((it as ArrayList<Assignment>))
            rvAssignments.adapter = rvAdapter
        }


        return binding.root
    }
}