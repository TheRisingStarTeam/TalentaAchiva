package com.risingstar.talentaachiva.feature.browseevent.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentDetailBinding
import com.risingstar.talentaachiva.domain.data.Event

class DetailFragment : Fragment() {
    private lateinit var viewmodel : DetailVM
    private lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DetailVM::class.java]

        viewmodel.currentEvent().observe(viewLifecycleOwner){
            if (it != null) {
                updateUI(it)
            }
        }

        return binding.root
    }

    private fun updateUI(it: Event) {
        
    }

}