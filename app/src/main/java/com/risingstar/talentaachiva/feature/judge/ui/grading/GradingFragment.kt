package com.risingstar.talentaachiva.feature.judge.ui.grading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentGradingBinding
import com.risingstar.talentaachiva.feature.judge.JudgeVM


class GradingFragment : Fragment() {
    private lateinit var binding : FragmentGradingBinding
    private lateinit var viewmodel : JudgeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewmodel = ViewModelProvider(requireActivity()).get(JudgeVM::class.java)
        binding = FragmentGradingBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

}