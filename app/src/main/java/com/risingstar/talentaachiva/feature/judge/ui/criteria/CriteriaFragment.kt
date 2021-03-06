package com.risingstar.talentaachiva.feature.judge.ui.criteria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentCriteriaBinding
import com.risingstar.talentaachiva.domain.data.Criteria
import com.risingstar.talentaachiva.feature.judge.JudgeVM
import com.risingstar.talentaachiva.feature.util.CriteriaAdapter

class CriteriaFragment : Fragment() {
    private lateinit var binding : FragmentCriteriaBinding
    private lateinit var viewmodel : JudgeVM
    private lateinit var rvCriteria:RecyclerView
    private lateinit var rvAdapter: CriteriaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(JudgeVM::class.java)
        binding = FragmentCriteriaBinding.inflate(layoutInflater,container,false)
        rvCriteria = binding.rvCriteria
        rvCriteria.layoutManager = LinearLayoutManager(this.context)

        viewmodel.assignment().observe(viewLifecycleOwner){
            if (it != null) {
                binding.etAssignName.text = it.title.toString()
                binding.etAssignDetail.text = it.description.toString()
                rvAdapter = CriteriaAdapter(it.criteria as ArrayList<Criteria>)
                rvCriteria.adapter = rvAdapter
            }
        }
        return binding.root
    }

}