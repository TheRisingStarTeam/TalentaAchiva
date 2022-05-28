package com.risingstar.talentaachiva.feature.browseevent.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.risingstar.talentaachiva.databinding.FragmentFakeDashboardBinding
import com.risingstar.talentaachiva.domain.data.Event
import com.risingstar.talentaachiva.feature.browseevent.dashboard.DebugVM
import com.risingstar.talentaachiva.feature.browseevent.dashboard.EventAdapter

class FakeDashboardFragment : Fragment() {

    private lateinit var binding : FragmentFakeDashboardBinding
    private lateinit var viewmodel : DebugVM

    private lateinit var rvEvent: RecyclerView
    private lateinit var rvAdapter : EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFakeDashboardBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[DebugVM::class.java]
        viewmodel.getEvents()

        rvEvent = binding.eventrv
        rvEvent.layoutManager = LinearLayoutManager(this.context)

        viewmodel.allEvents().observe(viewLifecycleOwner){list ->
            rvAdapter = EventAdapter(list as ArrayList<Event>)
            rvEvent.adapter = rvAdapter

            rvAdapter.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Event) {
                    TODO("Intent to Detail Event ")
                }
            })
        }

        return binding.root
    }
}

