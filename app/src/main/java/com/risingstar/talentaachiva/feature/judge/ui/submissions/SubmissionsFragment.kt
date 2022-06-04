package com.risingstar.talentaachiva.feature.judge.ui.submissions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentSubmissionsBinding


class SubmissionsFragment : Fragment() {
    private lateinit var binding : FragmentSubmissionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSubmissionsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}