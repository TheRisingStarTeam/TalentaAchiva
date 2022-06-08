package com.risingstar.talentaachiva.feature.dashboard.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentRunningEventBinding
import com.risingstar.talentaachiva.feature.dashboard.DashboardVM
import com.risingstar.talentaachiva.feature.util.RunningEventAdapter

class RunningEventFragment : Fragment() {

    private lateinit var binding : FragmentRunningEventBinding
    private lateinit var viewmodel : DashboardVM
    private lateinit var rvAdapter : RunningEventAdapter
    private lateinit var rvEvent : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRunningEventBinding.inflate(layoutInflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(DashboardVM::class.java)

        rvEvent = binding.rvRunningEvents
        rvEvent.layoutManager = LinearLayoutManager(requireActivity())

        viewmodel.runningEvent().observe(viewLifecycleOwner){events ->

        }

        return binding.root
    }
}