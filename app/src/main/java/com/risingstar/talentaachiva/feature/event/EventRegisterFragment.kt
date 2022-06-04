package com.risingstar.talentaachiva.feature.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.risingstar.talentaachiva.databinding.FragmentFakeRegEventBinding

class EventRegisterFragment : Fragment() {

    private lateinit var viewmodel : DetailVM
    private lateinit var binding : FragmentFakeRegEventBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        viewmodel.currentEvent().observe(viewLifecycleOwner){
            if (it != null) {
                binding.tvTos.text = it.tos
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
            viewmodel.registerEvent()
        }

        binding = FragmentFakeRegEventBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DetailVM::class.java]

        return binding.root
    }

    //Todo: Pindahin btnRegEvent ke Detail Activity
}