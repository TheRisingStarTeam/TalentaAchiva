package com.risingstar.talentaachiva.feature.judge.ui.criteria

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentCriteriaBinding

class CriteriaFragment : Fragment() {
    private lateinit var binding : FragmentCriteriaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCriteriaBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}