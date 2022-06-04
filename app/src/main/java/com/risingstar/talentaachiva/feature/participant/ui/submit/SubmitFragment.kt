package com.risingstar.talentaachiva.feature.participant.ui.submit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentSubmitBinding

class SubmitFragment : Fragment() {

    private lateinit var binding : FragmentSubmitBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentSubmitBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}