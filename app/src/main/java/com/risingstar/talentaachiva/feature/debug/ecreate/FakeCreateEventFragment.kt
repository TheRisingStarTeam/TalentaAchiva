package com.risingstar.talentaachiva.feature.debug.ecreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentFakeCreateEventBinding

class FakeCreateEventFragment : Fragment() {

    private lateinit var binding : FragmentFakeCreateEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeCreateEventBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}