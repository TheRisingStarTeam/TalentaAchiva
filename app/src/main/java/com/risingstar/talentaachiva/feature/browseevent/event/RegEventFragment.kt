package com.risingstar.talentaachiva.feature.browseevent.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentRegEventBinding
import com.risingstar.talentaachiva.domain.data.Event

class RegEventFragment : Fragment() {

    private lateinit var viewmodel : DetailVM
    private lateinit var binding : FragmentRegEventBinding
    private var event: Event? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        viewmodel.currentEvent().observe(viewLifecycleOwner){
            if (it != null) {
                binding.tvTos.text = it.tos
                event = it
                binding.btnRegEvent.isEnabled = true
            }
        }

        viewmodel.registerEventResult().observe(viewLifecycleOwner){
            if(it==true) {
                Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(requireActivity(),"Failed",Toast.LENGTH_SHORT).show()
        }

        binding.btnRegEvent.setOnClickListener {
            viewmodel.registerEvent(event!!)
        }

        binding = FragmentRegEventBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DetailVM::class.java]

        return binding.root
    }
}