package com.risingstar.talentaachiva.feature.judge.ui.grading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentGradingBinding


class GradingFragment : Fragment() {
    private lateinit var binding : FragmentGradingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentGradingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}