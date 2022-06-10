package com.risingstar.talentaachiva.feature.participant.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.R
import com.risingstar.talentaachiva.databinding.FragmentInstructionBinding
import com.risingstar.talentaachiva.domain.data.Criteria
import com.risingstar.talentaachiva.feature.participant.ParticipantVM
import com.risingstar.talentaachiva.feature.util.CriteriaAdapter


class InstructionFragment : Fragment() {

    private lateinit var binding : FragmentInstructionBinding
    private lateinit var viewmodel : ParticipantVM
    private lateinit var rvAdapter : CriteriaAdapter
    private lateinit var rvCriteria : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(ParticipantVM::class.java)
        binding = FragmentInstructionBinding.inflate(layoutInflater,container,false)
        rvCriteria = binding.rvCriteria
        rvCriteria.layoutManager = LinearLayoutManager(requireActivity())
        viewmodel.assignment().observe(viewLifecycleOwner){
            binding.tvTitleSubmission.text = it?.title?:"Instruction"
            binding.tvDetailInstruction.text = it?.description ?: "No Description provided"
            rvAdapter = CriteriaAdapter(it?.criteria as ArrayList<Criteria>)
            rvCriteria.adapter = rvAdapter

        }
        binding.buttonSubmitInstruction.setOnClickListener {
            it.findNavController().navigate(R.id.navigate_submit)
        }

        return binding.root
    }
}