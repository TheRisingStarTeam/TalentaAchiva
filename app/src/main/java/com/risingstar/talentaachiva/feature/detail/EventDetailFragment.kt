package com.risingstar.talentaachiva.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentEventDetailBinding


class EventDetailFragment : Fragment() {
    private lateinit var binding : FragmentEventDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEventDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}