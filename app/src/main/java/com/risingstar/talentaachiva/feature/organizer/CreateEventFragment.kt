package com.risingstar.talentaachiva.feature.organizer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.risingstar.talentaachiva.databinding.FragmentCreateEventBinding

class CreateEventFragment : Fragment() {
    private lateinit var binding : FragmentCreateEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateEventBinding.inflate(layoutInflater,container,false)

        return binding.root
    }
}