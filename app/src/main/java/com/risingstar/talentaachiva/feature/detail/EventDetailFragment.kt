package com.risingstar.talentaachiva.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentEventDetailBinding


class EventDetailFragment : Fragment() {
    private lateinit var binding : FragmentEventDetailBinding
    private lateinit var viewmodel : DetailVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        viewmodel = ViewModelProvider(requireActivity()).get(DetailVM::class.java)
        binding = FragmentEventDetailBinding.inflate(layoutInflater,container,false)

        viewmodel.events().observe(viewLifecycleOwner){
            binding.tvEventTitle.text = it?.name?:"No Title Provided"
            binding.tvDetailEvent.text = it?.description ?: "No Description Provided"
        }

        binding.btnRegisterEvent.setOnClickListener {
            viewmodel.registerEvent()
        }

        return binding.root
    }
}