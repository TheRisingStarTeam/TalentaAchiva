package com.risingstar.talentaachiva.feature.management.ui.assignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentAssignmentsBinding
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.feature.detail.DetailActivity
import com.risingstar.talentaachiva.feature.judge.JudgeActivity
import com.risingstar.talentaachiva.feature.management.ManagementVM
import com.risingstar.talentaachiva.feature.participant.ParticipantActivity
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
        rvAssignments.layoutManager = LinearLayoutManager(requireActivity())

        viewmodel.assignments().observe(viewLifecycleOwner){
            if(it!=null) {
                rvAdapter = AssignmentAdapter((it as ArrayList<Assignment>))
                rvAssignments.adapter = rvAdapter
                rvAdapter.setOnItemClickCallback(object : AssignmentAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Assignment) {
                        showSelected(data)
                    }
                })
            }

            if(viewmodel.currentEvent.organizers?.contains(viewmodel.userId) == true)
                binding.floatingActionButton2.isVisible = true
            binding.floatingActionButton2.setOnClickListener { view ->
                view.findNavController().navigate(R.id.navigate_create_assignment)
            }
        }


        return binding.root
    }

    private fun showSelected(assignment: Assignment) {
        if(viewmodel.currentEvent.participants?.contains(viewmodel.userId) == true){
            val intent = Intent(this.context, DetailActivity::class.java)
            intent.putExtra(ParticipantActivity.CURRENT_ASSIGNMENT_ID,assignment)
            intent.putExtra(ParticipantActivity.CURRENT_USER,viewmodel.userId)
            startActivity(intent)
        }else{
            val intent = Intent(this.context, JudgeActivity::class.java)
            intent.putExtra(JudgeActivity.CURRENT_ASSIGNMENT_ID,assignment)
            intent.putExtra(JudgeActivity.CURRENT_USER_ID,viewmodel.userId)
            startActivity(intent)
        }
    }
}