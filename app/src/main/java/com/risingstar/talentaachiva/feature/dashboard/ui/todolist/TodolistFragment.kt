package com.risingstar.talentaachiva.feature.dashboard.ui.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentTodolistBinding
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM
import com.risingstar.talentaachiva.feature.util.AssignmentAdapter

class TodolistFragment : Fragment() {
    private lateinit var viewmodel : DashboardVM
    private var _binding: FragmentTodolistBinding? = null
    private lateinit var rvAssignments: RecyclerView
    private lateinit var rvAdapter: AssignmentAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(DashboardVM::class.java)
        _binding = FragmentTodolistBinding.inflate(inflater, container, false)

        rvAssignments = binding.rvTodolist
        viewmodel.allAssignments().observe(viewLifecycleOwner){
            rvAdapter = AssignmentAdapter(it as ArrayList<Assignment>)
            rvAssignments.adapter = rvAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}