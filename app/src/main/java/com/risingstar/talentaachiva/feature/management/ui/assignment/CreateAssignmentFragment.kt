package com.risingstar.talentaachiva.feature.management.ui.assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentCreateAssignmentBinding
import com.risingstar.talentaachiva.domain.data.Assignment
import com.risingstar.talentaachiva.domain.data.Criteria
import com.risingstar.talentaachiva.feature.management.ManagementVM
import com.risingstar.talentaachiva.feature.util.CriteriaAdapter

class CreateAssignmentFragment : Fragment() {

    private lateinit var binding : FragmentCreateAssignmentBinding
    private lateinit var viewmodel : ManagementVM
    private lateinit var rvCriteria: RecyclerView
    private lateinit var rvAdapter : CriteriaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateAssignmentBinding.inflate(layoutInflater,container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(ManagementVM::class.java)

        rvCriteria = binding.rvCriteria
        rvCriteria.layoutManager = LinearLayoutManager(this.context)
        rvAdapter = CriteriaAdapter(mutableListOf<Criteria>() as ArrayList<Criteria>)
        rvCriteria.adapter = rvAdapter
        binding.btnAddCriteria.setOnClickListener {
            rvAdapter.addCriteria(
                Criteria(
                    binding.tvCritName.text.toString(),
                    null,
                    binding.tvCritNum.text.toString().toInt())
            )
            rvCriteria.adapter?.notifyDataSetChanged()
        }

        binding.btnSubmitAssignment.setOnClickListener {
            viewmodel.createAssignment(
                Assignment(
                    null,
                    binding.etAssignName.text.toString(),
                    null,
                    binding.etAssignDetail.text.toString(),
                    null,
                    criteria = rvAdapter.getCriteria(),
                    eventId = viewmodel.eventId
                )
            )
        }
        return binding.root

    }
}