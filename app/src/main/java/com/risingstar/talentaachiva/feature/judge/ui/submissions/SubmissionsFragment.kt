package com.risingstar.talentaachiva.feature.judge.ui.submissions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentSubmissionsBinding
import com.risingstar.talentaachiva.domain.data.Submissions
import com.risingstar.talentaachiva.feature.judge.JudgeVM
import com.risingstar.talentaachiva.feature.util.SubmissionAdapter


class SubmissionsFragment : Fragment() {
    private lateinit var binding : FragmentSubmissionsBinding
    private lateinit var viewmodel: JudgeVM
    private lateinit var rvAdapter: SubmissionAdapter
    private lateinit var rvSubmission: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSubmissionsBinding.inflate(layoutInflater,container,false)
        viewmodel = ViewModelProvider(requireActivity()).get(JudgeVM::class.java)

        rvSubmission = binding.rvSubmission
        rvSubmission.layoutManager = LinearLayoutManager(this.context)

        viewmodel.submission().observe(viewLifecycleOwner){
            if(it!=null){
                rvAdapter = SubmissionAdapter(it as ArrayList<Submissions>)
                rvSubmission.adapter = rvAdapter

            }
        }

        return binding.root
    }


}