package com.risingstar.talentaachiva.feature.dashboard.ui.todolist

import android.content.Intent
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
import com.risingstar.talentaachiva.feature.participant.ParticipantActivity
import com.risingstar.talentaachiva.feature.util.AssignmentAdapter

class TodolistFragment : Fragment() {
    private lateinit var viewmodel : DashboardVM
    private var _binding: FragmentTodolistBinding? = null
    private lateinit var rvAssignments: RecyclerView
    private lateinit var rvAdapter: AssignmentAdapter

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
            rvAdapter.setOnItemClickCallback(object : AssignmentAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Assignment) {
                    showSelected(data)
                }
            })
        }

        return binding.root
    }

    fun showSelected(assignment:Assignment){
        val intent = Intent(this.context, ParticipantActivity::class.java)
        intent.putExtra(ParticipantActivity.CURRENT_ASSIGNMENT_ID,assignment)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}